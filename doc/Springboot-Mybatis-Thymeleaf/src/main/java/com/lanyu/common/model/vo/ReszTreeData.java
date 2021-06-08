package com.lanyu.common.model.vo;

/**
 * 返回zTree数据格式数据
 * { id:221, pId:22, name:"随意勾选 2-2-1", checked:true,open:false}
 */
public class ReszTreeData {

    private String id;
    private String pId;
    private String name;
    private Boolean checked;
    private Boolean open = true;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
