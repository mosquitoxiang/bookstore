package com.example.administrator.modelmall.entity;

public class BookCategoryBean {


    /**
     * code : 200
     * msg : 展示成功
     * data : {"position":0,"main_image":"http://img14.360buyimg.com/n1/jfs/t1/1208/33/4505/162630/5b9c7498E6b8e9617/d5016d201f59add9.jpg","categoryItem":{"id":32,"item_image1":"http://img3m5.ddimg.cn/4/29/25289455-1_l_1.jpg","item_image2":"http://img3m5.ddimg.cn/45/11/23931315-1_l_19.jpg","item_image3":"http://img3m2.ddimg.cn/38/20/23950712-1_l_7.jpg","item_image4":"http://img3m2.ddimg.cn/80/18/25227062-1_l_1.jpg","item_image5":"http://img3m0.ddimg.cn/54/4/25230600-1_l_5.jpg","item_image6":"http://img3m9.ddimg.cn/90/35/25067979-1_l_3.jpg","position":0}}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * position : 0
         * main_image : http://img14.360buyimg.com/n1/jfs/t1/1208/33/4505/162630/5b9c7498E6b8e9617/d5016d201f59add9.jpg
         * categoryItem : {"id":32,"item_image1":"http://img3m5.ddimg.cn/4/29/25289455-1_l_1.jpg","item_image2":"http://img3m5.ddimg.cn/45/11/23931315-1_l_19.jpg","item_image3":"http://img3m2.ddimg.cn/38/20/23950712-1_l_7.jpg","item_image4":"http://img3m2.ddimg.cn/80/18/25227062-1_l_1.jpg","item_image5":"http://img3m0.ddimg.cn/54/4/25230600-1_l_5.jpg","item_image6":"http://img3m9.ddimg.cn/90/35/25067979-1_l_3.jpg","position":0}
         */

        private int position;
        private String main_image;
        private CategoryItemBean categoryItem;

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        public String getMain_image() {
            return main_image;
        }

        public void setMain_image(String main_image) {
            this.main_image = main_image;
        }

        public CategoryItemBean getCategoryItem() {
            return categoryItem;
        }

        public void setCategoryItem(CategoryItemBean categoryItem) {
            this.categoryItem = categoryItem;
        }

        public static class CategoryItemBean {
            /**
             * id : 32
             * item_image1 : http://img3m5.ddimg.cn/4/29/25289455-1_l_1.jpg
             * item_image2 : http://img3m5.ddimg.cn/45/11/23931315-1_l_19.jpg
             * item_image3 : http://img3m2.ddimg.cn/38/20/23950712-1_l_7.jpg
             * item_image4 : http://img3m2.ddimg.cn/80/18/25227062-1_l_1.jpg
             * item_image5 : http://img3m0.ddimg.cn/54/4/25230600-1_l_5.jpg
             * item_image6 : http://img3m9.ddimg.cn/90/35/25067979-1_l_3.jpg
             * position : 0
             */

            private int id;
            private String item_image1;
            private String item_image2;
            private String item_image3;
            private String item_image4;
            private String item_image5;
            private String item_image6;
            private int position;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getItem_image1() {
                return item_image1;
            }

            public void setItem_image1(String item_image1) {
                this.item_image1 = item_image1;
            }

            public String getItem_image2() {
                return item_image2;
            }

            public void setItem_image2(String item_image2) {
                this.item_image2 = item_image2;
            }

            public String getItem_image3() {
                return item_image3;
            }

            public void setItem_image3(String item_image3) {
                this.item_image3 = item_image3;
            }

            public String getItem_image4() {
                return item_image4;
            }

            public void setItem_image4(String item_image4) {
                this.item_image4 = item_image4;
            }

            public String getItem_image5() {
                return item_image5;
            }

            public void setItem_image5(String item_image5) {
                this.item_image5 = item_image5;
            }

            public String getItem_image6() {
                return item_image6;
            }

            public void setItem_image6(String item_image6) {
                this.item_image6 = item_image6;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }
        }
    }
}
