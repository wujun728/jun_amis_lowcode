var Img = {
    
    //uni 图片转base64
    canvasToBase64(res) {
        //res={tempFilePath,fileType}
        var fileType = res.fileType || 'jpeg';
        return new Promise((resolve, reject) => {
            //#ifdef MP-WEIXIN
            wx.getFileSystemManager().readFile({
                filePath: res.tempFilePath, //选择图片返回的相对路径
                encoding: 'base64', //编码格式
                success: res => { //成功的回调
                    resolve('data:image/' + fileType + ';base64,' + res.data);
                }
            })
            //#endif
            //#ifdef APP-PLUS
            // console.log("app_plus")
            plus.io.resolveLocalFileSystemURL(res.tempFilePath, function(entry) {
                // 可通过entry对象操作test.html文件
                entry.file(function(file) {
                    var fileReader = new plus.io.FileReader();
                    fileReader.readAsDataURL(file);
                    fileReader.onloadend = function(evt) {
                        // console.log(JSON.stringify(evt))
                        resolve(evt.target.result); ////base64字符串  
                    }
                })
            })
            //#endif
            //#ifdef H5
            // console.log("H5")
            resolve(res.tempFilePath)
            // reject({message:'yc_js.Img暂不支持h5端使用，请下载app进行操作'})
            //#endif
        })
    },imgsToBase64(imgs, base64All = []) {
//uni 多图片转base64 imgs=[{tempFilePath,fileType}]
        var num = imgs.length;
        return new Promise((resolve, reject) => {
            // console.log({imgs,base64All})
            if (num > 0) {
                var img = imgs[0];
                imgs = imgs.filter((e, idx) => {
                    if (idx != 0) {
                        return e
                    }
                })
                this.canvasToBase64(img)
                    .then(e => {
                        base64All.push(e)
                        this.imgsToBase64(imgs, base64All).then(e => {
                            // console.log('彻底结束出口')
                            resolve(e)
                        })
                    })
                    .catch(e => {
                        uni.showToast({
                            title: '失败！' + e.message,
                            icon: 'none',
                            duration: 1000
                        });
                    })
            } else {
                // console.log(['递归结束了',base64All])
                resolve(base64All)
            }

        })
    },
    arrayBufferToImg(val) {
        var captcha = false;
        //  #ifdef MP-WEIXIN
        let base64 = wx.arrayBufferToBase64(val);
        captcha = 'data:image/jpg;base64,' + base64;
        //  #endif
        // #ifdef H5 || APP-PLUS
        var bytes = new Uint8Array(val);
        var data = "";
        var len = bytes.byteLength;
        for (var i = 0; i < len; ++i) {
            data += String.fromCharCode(bytes[i]);
        }
        captcha = "data:image/png;base64," + window.btoa(data);
        // #endif
        return captcha
    }
}
export default Img
