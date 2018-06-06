$(function () {
    initDataGrid();
    });

function initDataGrid()
{
    //创建bootstrapTable
    $("#redisTest_dataGrid").bootstrapTable({
        method:"GET",
        contentType : "application/x-www-form-urlencoded;charset=UTF-8",  //极为重要，缺失无法执行queryParams，传递page参数
        dataType:"json",
        url:'/redisTest/getAll',
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
            field : "name"
        }, {
            title : "手机",
            field : "tel"
        }, {
            title : "住址",
            field : "address"
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
}


//添加用户
/* 使用直接调用静态页面的形式调用html（将资源文件路径下的templates文件夹设置为可访问静态路径）*/
function addTest()
{
    $.ajax({
        url:"/redisTest/add",
        // contentType:"application/json;charset=UTF-8",
        method:"GET",
        // data:JSON.stringify(getSelectRows),
        success:function (data) {
            $("#redisTest_dataGrid").bootstrapTable("refresh");
        }
    })
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

