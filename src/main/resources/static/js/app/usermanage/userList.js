require(['js/main.js'],function (main) {
    requirejs(['jquery','layer','bootstrap','bootstrapTable','bootstrap_table_mobile','bootstrap_table_zh_CN','content'],function ($,layer) {
        $(function () {
            initDataGrid();



            $("#btn_add").on("click",function () {
                parent.layer.open({
                    type: 2,
                    title:'添加用户',
                    area:['800px','330px'],
                    offset: 'auto', //具体配置参考：offset参数项
                    content: 'userAdd.html',
                    shade: 0.4, //不显示遮罩
                    end: function () {
                        $("#user_dataGrid").bootstrapTable("refresh");
                        // window.location.reload();   //刷新父页面
                    }
                });

            })
        });


        function initDataGrid()
        {
            //创建bootstrapTable
            $("#user_dataGrid").bootstrapTable({
                method:"POST",
                contentType : "application/x-www-form-urlencoded;charset=UTF-8",  //极为重要，缺失无法执行queryParams，传递page参数
                dataType:"json",
                url:'/userPageInfo',
                queryParams:queryParam,
                pagination:true,//显示分页条：页码，条数等
                striped:true,//隔行变色
                pageNumber:1,//首页页码
                pageSize:10,//分页，页面数据条数
                uniqueId:"id",//Indicate an unique identifier for each row
                sidePagination:"server",//在服务器分页
                height:"auto",
                toolbar:"#toolbar",//工具栏
                clickToSelect: true,
                columns : [{
                    checkbox:"true",
                    field : "box"
                },  {
                    title : "ID",
                    field : "id",
                    visible: false
                }, {
                    title : "账户",
                    field : "username"
                }, {
                    title : "密码",
                    type: "password",
                    field : "password"
                }, {
                    title : "类型",
                    field : "type"
                }, {
                    title : "状态",
                    field : "status"
                }, {
                    title : "操作",
                    field : "",
                    formatter: function (value, row, index) {
                        return btnFormat(value,row,index);
                    }

                }],
                search : true,//搜索
                searchOnEnterKey : true,
                showRefresh : true,//刷新
                showToggle : true//

            });
        }

        function queryParam(params)
        {
            var param  = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
                // limit: this.limit,
                // offset: this.offset,
                pageNumber: this.pageNumber,
                pageSize: this.pageSize
            };
            return param ;
        }

//格式化列表操作按钮
        function btnFormat(value,row,index)
        {
            var content = "";

            /*content +=
                '&nbsp;<button  class="btn btn-success btn-xs" data-toggle="modal" data-target="#modal_detail" onclick="detail('
                + id + ',\''+uuid+'\')" type="button">详&nbsp;&nbsp;情</button>&nbsp;';*/

            content+= '&nbsp;<button  class="btn btn-success btn-xs" data-toggle="modal" data-target="#modal_publish" onclick="editUser('
                + row.id + ')" type="button">修&nbsp;&nbsp;改</button>&nbsp;';

            /*content +='&nbsp;<button  class="btn btn-success btn-xs" type="button" data-toggle="modal" data-target="#del_Modal" onclick="deletedaily('
                + row.id+ ',\''+uuid+'\')">删&nbsp;&nbsp;除</button>&nbsp;';*/
            return content;
        }


//编辑用户
        function editUser(id)
        {
            parent.layer.open({
                type: 2     //type的值可以为：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                ,skin: 'layui-layer-rim'
                ,title:'修改用户'
                ,area:['870px','330px']
                ,offset: 'auto' //具体配置参考：offset参数项
                ,content: "/toModify?id="+id
                ,shade: 0.4 //不显示遮罩
            });
        }


//添加用户
        /* 使用直接调用静态页面的形式调用html（将资源文件路径下的templates文件夹设置为可访问静态路径）*/
        function addUser()
        {
            layer.open({
                type: 2,
                title:'添加用户',
                area:['800px','330px'],
                offset: 'auto', //具体配置参考：offset参数项
                content: 'userAdd.html',
                shade: 0.4, //不显示遮罩
                end: function () {
                    $("#user_dataGrid").bootstrapTable("refresh");
                    // window.location.reload();   //刷新父页面
                }
            });
        }


//批量删除
        function batchDelete() {
            var getSelectRows = $("#user_dataGrid").bootstrapTable("getSelections",function (row) {
                return row;
            });
            $.ajax({
                url:"/deleteInBatchUser",
                contentType:"application/json;charset=UTF-8",
                type:"post",
                data:JSON.stringify(getSelectRows),
                success:function (data) {
                    $("#user_dataGrid").bootstrapTable("refresh");
                }
            })
        }

    })
})





