<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<jsp:include page="/WEB-INF/views/admin/common/header.jsp"></jsp:include>
    <title>${viewName}信息管理</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/public/css/loading.css">
    <style type="text/css">
        .fitem label { width:160px;text-align:right; }
    </style>
</head>
<body>
<div region="center" style="padding:5px;" border="false"> 
    <div id="toolbar" class="hid">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newItem()">新增</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItem()">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeItem()">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="$('#dg').datagrid('reload');">刷新</a>
    </div>
    
    <table id="dg" class="easyui-datagrid hid" style="width:100%;height:540px;">
        <thead>
            <tr>
                ${listStr}            </tr>
        </thead>
    </table>
    
    <div id="dlginfo" class="easyui-dialog hid" style="width:820px;height:420px;padding:10px 20px;"
            closed="true" buttons="#dlginfo-buttons">
        <div class="ftitle">编辑${viewName}信息</div>
        <form id="infoForm" method="post" novalidate>
            <div class="fitem" style="margin-top:24px;">
                <label>企业名称：</label>
                <input class="easyui-combobox liw" id="enterpriseName" name="enterpriseName" required="true">
            </div>
            <div class="fitem">
                <label>${viewName}信息时间：</label>
                <input id="dataTime" name="dataTime" type="text" class="easyui-textbox iw" required="true" validType="isName">
            </div>
            <div class="fitem">
                <label>固定资产：</label>
                <input id="fixedAssets" name="fixedAssets" type="text" class="easyui-textbox iw" required="true" validType="isName">
                <label style="margin-left:146px;width:98px;">无形资产：</label>
                <input id="intangibleAssets" name="intangibleAssets" type="text" class="easyui-textbox iw" required="true" validType="mobile">
            </div>
            <div class="fitem">
                <label>短期借款：</label>
                <input id="shortTermBorrowing" name="shortTermBorrowing" type="text" class="easyui-textbox iw" required="true" validType="isName">
                <label style="margin-left:146px;width:98px;">长期借款：</label>
                <input id="longTermBorrowing" name="longTermBorrowing" type="text" class="easyui-textbox iw" required="true" validType="mobile">
            </div>
            <div class="fitem">
                <label>实收资本：</label>
                <input id="paidUpCapital" name="paidUpCapital" type="text" class="easyui-textbox iw" required="true" validType="isName">
                <label style="margin-left:146px;width:98px;">期末存货：</label>
                <input id="closedInventory" name="closedInventory" type="text" class="easyui-textbox iw" required="true" validType="mobile">
            </div>
            <div class="fitem">
                <label>应收账款期末余额：</label>
                <input id="closedMoney" name="closedMoney" type="text" class="easyui-textbox iw" required="true" validType="isName">
                <label style="margin-left:146px;width:98px;">主营业务收入：</label>
                <input id="mastIncome" name="mastIncome" type="text" class="easyui-textbox iw" required="true" validType="mobile">
            </div>
            <div class="fitem">
                <label>主营业务成本：</label>
                <input id="mastCost" name="mastCost" type="text" class="easyui-textbox iw" required="true" validType="isName">
                <label style="margin-left:146px;width:98px;">净利润：</label>
                <input id="netProfits" name="netProfits" type="text" class="easyui-textbox iw" required="true" validType="mobile">
            </div>
        </form>
    </div>
    <div id="dlginfo-buttons" class="hid">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveInfo()">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlginfo').dialog('close')">取消</a>
    </div>
</div>

<script type="text/javascript">
    // 页面加载
    $(document).ready(function() {
        $('#dg').datagrid({
            url: 'list',
            iconCls: 'icon-ok',
            toolbar: '#toolbar',
            collapsible: true,
            rownumbers: false,
            animate: true,
            fitColumns: true,
            singleSelect: true,
            pageSize: 12,
            pagination: true,
            loadMsg: '数据加载中请稍后……',
            method: 'post',
            idField: 'id',
            pageList: [12,24,36,48],
        });
    });
    
    var url;
    function newItem() {
        $('#dlginfo').dialog('open').dialog('setTitle', '添加${viewName}数据');
        $('#infoForm').form('clear');
        url = 'save';
    }
    
    function editItem() {
        var row = $('#dg').datagrid('getSelected');
        if (row) {
            $('#dlginfo').dialog('open').dialog('setTitle','修改${viewName}数据');
            $('#infoForm').form('load',row);
            url = 'update?id=' + row.id;
        }
    }
    
    function saveItem() {
        $('#infoForm').form('submit',{
            url: url,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result) {
                var result = eval('('+result+')');
                if (result.success){
                    $('#dlginfo').dialog('close');      // close the dialog
                    $('#dg').datagrid('reload');    // reload the sysMenu data
                } else {
                    $.messager.show({
                        title: 'Error',
                        msg: result.msg
                    });
                }
            }
        });
    }

    function removeItem() {
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('删除','你确定要删除这条数据吗?',function(r) {
                if (r) {
                    $.post('delete',{id:row.id},function(result) {
                        if (result.success) {
                            $('#dg').datagrid('reload');    // reload the travelStrategy data
                        } else {
                            $.messager.show({   // show error message
                                title: 'Error',
                                msg: result.msg
                            });
                        }
                    },'json');
                }
            });
        }
    }
</script>

</body>
</html>