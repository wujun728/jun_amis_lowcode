## layui扩展-图片管理

图片文件管理插件
支持远程图片显示
可配置默认文件类型图标
配置操作与layui-table类似;
更多功能请下载试用

![输入图片说明](https://images.gitee.com/uploads/images/2019/1226/212705_c5790b64_60481.png "filemanage.jpg")

## 使用示例

```
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>图库管理power by www.nbnat.com</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="https://www.layuicdn.com/layui/css/layui.css" />
</head>
<body style='padding:10px'>
    <button type="button" class="layui-hide" id="test1"></button>
    <div class="layui-fluid">
        <div id="fileManager" lay-filter="test" ></div>
    </div>
</body>
<script src="https://www.layuicdn.com/layui/layui.js"></script>
<script>
    layui.extend({'fileManager':'ext/fileManager'});
    layui.use(['fileManager','layer','upload'], function () {
        var fileManager = layui.fileManager
            ,$ = layui.$ 
            ,upload = layui.upload
            ,layer = layui.layer;
        $('title').html($('title').html()+' version:'+fileManager.v);
        var upIns = upload.render({
                elem: '#test1' //绑定元素
                ,url: 'data.php?action=upload' //上传接口
                ,field:'file[]'
        })
        fileManager.render({
            elem: '#fileManager'
            , method:'post'
            , id: 'fmTest'
            , btn_upload: true
            , btn_create: true
            , url: 'data.php?action=get_file_data'
            , thumb: {'nopic':'/filemanage/upload/null-100x100.jpg','width':100,'height':100}
            , parseData: function (res) {
                /*
                data:[{
                    thumb:文件地址用于显示
                    ,type:文件类型  directory文件夹,png|gif|png|image图片,其它任意
                    ,path:文件夹路径用于打开本文件夹
                }]
                */
                let _res = [];
                _res.code = 0;
                _res.data = res.images;
                _res.count = res.count
                return _res;
            }
            , done: function (res,curr,count) {
                // console.log(res,curr,count)
            }
            , page: {limit:12}
            , where: {action: 'get_file_data'}
        });
        //监听图片选择事件
        fileManager.on('pic(test)', function(obj){
            //obj.obj 当前对象
            //obj.data 当前图片数据
            var data = obj.data;
            layer.alert(JSON.stringify(data), {
            title: '当前数据：'
            });
        });
         //监听图片上传事件
         fileManager.on('uploadfile(test)', function(obj){
            //obj.obj 当前对象
            //obj.path 路径
            //更改上传组件参数
            upIns.config.data={'path':obj.path};
            upIns.config.done = function(res){
                fileManager.reload('fmTest');
            }
            var e = document.createEvent("MouseEvents");
            e.initEvent("click", true, true);
            document.getElementById("test1").dispatchEvent(e)
        });
        //监听新建文件夹事件
        fileManager.on('new_dir(test)', function(obj){
            //obj.obj 当前对象
            //obj.folder 文件夹名称
            //obj.path 路径
            e = JSON.parse(e);
            $.post('data.php?action=folder',{'folder':obj.folder,'path':obj.path},function(e){
                layer.msg(e.msg);
                if(e.code == 1){
                    fileManager.reload('fmTest');
                }
            })
        });
    });
</script>
</html>
```

## 基础参数

| 参数 | 类型       | 说明                 | 示例         |
| ---- | ---------- | -------------------- | ------------ |
| elem | String/DOM | 容器的选择器或       | "#demo"      |
| id   | String     | 设定容器唯一 id      | test         |
| url  | -          | 异步数据接口相关参数 | [详见异步接口](https://www.layui.com/doc/modules/table.html#async) |
| icon_url  | String          | 文件类型默认图标, |  'http://test.hd/filemanage/ico/' |
| btn_uplopd   | Boolean     | 是否显示上传按钮      | false         |
| btn_create   | Boolean     | 是否显示新建文件夹按钮      | false         |
| thumb   | Array     | 图片显示配置      | {'nopic':'100.jpg','width':100,'height':100} |
| page   | Boolean/Object     | 分页设置      | [详见分页接口](https://www.layui.com/doc/modules/laypage.html#options)         |
| done | Function   | 渲染完的回调         | 详见示例     |

## 基础方法

### 当前版本

```
fileManager.v;
```

### 重载

参数 ID         即为基础参数id对应的值，见：设定容器唯一ID
参数 options    即为各项基础参数

```
fileManager.reload('fmTest',{where:{'test':'reload'}});
```


## 事件监听

### 图片选择事件

- 详见示例

### 图片上传事件

- 详见示例

### 新建文件夹事件

- 详见示例

