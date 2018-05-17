$(function () {
    initDataGrid();
    });

function initDataGrid()
{
    //创建bootstrapTable
    $("#user_dataGrid").bootstrapTable({
        method:"POST",
        contentType : "application/x-www-form-urlencoded",  //极为重要，缺失无法执行queryParams，传递page参数
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
            field : "password"
        }, {
            title : "类型",
            field : "type"
        }, {
            title : "状态",
            field : "status"
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
        limit: this.limit,
        offset: this.offset,
        pageNumber: this.pageNumber,
        pageSize: this.pageSize
    };
    return param ;
}

