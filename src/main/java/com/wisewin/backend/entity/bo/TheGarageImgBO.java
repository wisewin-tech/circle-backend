package com.wisewin.backend.entity.bo;

public class TheGarageImgBO {
    private Integer id; //车库图片
    private Integer theGarageId; //车库主键
    private String img; //图片路径
    private Integer sort; //排序

    @Override
    public String toString() {
        return "TheGarageImgBO{" +
                "id=" + id +
                ", theGarageId=" + theGarageId +
                ", img='" + img + '\'' +
                ", sort=" + sort +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTheGarageId() {
        return theGarageId;
    }

    public void setTheGarageId(Integer theGarageId) {
        this.theGarageId = theGarageId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
