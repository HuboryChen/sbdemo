$(function () {
    alert(0);
    $("#usertable").bootstrapTable({
        url: "/findUserForPage",      //请求后台的URL（*）
        method: "POST",      //请求方式（*）
        toolbar: "#toolbar",   //工具按钮用哪个容器
        striped: true,      //是否显示行间隔色
        cache: false,       //是否使用缓存，默认为true,所以一般情况下需要设置一下这个属性（*）
        pagination: true,   //是否显示分页（*）
        sortable: true,    //是否启用排序
        sortOrder : "desc",     //排序方式
        sortName: "id",
        queryParams: getQueryparam, //传递参数（*），这里应该返回一个object，即形如{param1:val1,param2:val2}
        queryParamsType: "limit", //参数格式,发送标准的RESTFul类型的参数请求

        sidePagination: "server", //分页方式：server：服务端分页；client：客户端分页
        pageNumber:1,       //初始化加载第一页，默认第一页
        pageSize: 15,       //每页的记录行数（*）
        pageList: [15, 30, 45], //可供选择的每页的行数（*）
        // search: false,       //是否显示表格搜索，此搜索是客户端搜索，不会进服务器端

        // strictSearch: true,
        // showColumns: true,  //是否显示所有的列
        showRefresh: true,  //是否显示刷新按钮
        // minimumCountColumns: 2, //最少允许的列数
        clickToSelect: true,    //是否启用点击选中行
        // height: "auto",  //行高，如果没有设置height属性，表格自动根据记录条数调整表格高度
        // uniqueId: "id",
        showToggle: true,   //是否显示详细视图和列表视图的切换按钮
        cardView: false,    //是否显示详细视图
        detailView: false,  //是否显示父子表
        idField: "id",

        columns: [
            {
                field: 'xuhao',
                title: '序号',
                align: 'center',
                halign: 'center',
                width: "5%",
                formatter: function (value, row, index) {
                    return index + 1;
                }
            }, {
                field: 'username',
                title: '姓名',
                align: 'center',
                halign: 'center',
                width: "5%",
            }, {
                field: 'password',
                title: '密码',
                halign: 'center',
                align: 'center',
                width: "5%",
            }, {
                field: 'type',
                title: '用户类型',
                align: 'center',
                halign: 'center',
                width: "5%",
            }, {
                field: 'status',
                title: '状态',
                halign: 'center',
            }, {
                field: '',
                title: '操作',
                align: 'center',
                width: "15%",
                // formatter: function (value, row, index) {
                //     return btnFormat(value, row, index);
                // }
            }]
    });
    alert(1);

    function queryParams(params)
    {
        alert();
        var queryJson = {"username":"","type":""};
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            limit: params.limit,
            offset: params.offset,
            sort: params.sort,
            order: params.order,
            queryparam: JSON.stringify(queryJson)
        };
        return temp;
    }

})