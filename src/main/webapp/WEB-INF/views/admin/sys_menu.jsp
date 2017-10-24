<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<jsp:include page="/WEB-INF/views/admin/common/header.jsp"></jsp:include>
    <title>系统菜单管理</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/public/css/switch-button.css">
</head>
<body>
<div region="center" style="padding:5px;" border="false">
    <div id="toolbar" class="hid">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newsysMenu()">新增</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editsysMenu()">编辑</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeItem()">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-reload" plain="true" onclick="$('#dg').datagrid('reload');">刷新</a>
    </div>

    <table id="dg" class="easyui-treegrid hid" style="width:100%;height:540px;"
            data-options="
                url: 'alltree',
                iconCls: 'icon-ok',
                toolbar: '#toolbar',
                collapsible: true,
                rownumbers: false,
                animate: true,
                fitColumns: true,
                singleSelect: true,
                loadMsg: '数据加载中请稍后……',
                method: 'get',
                idField: 'id',
                treeField: 'text'
            ">
        <thead>
            <tr>
                <th data-options="field:'id',width:30">序号</th>
                <th data-options="field:'text',width:180">菜单名称</th>
                <th data-options="field:'icon',width:80">菜单图标</th>
                <th data-options="field:'url',width:210">访问路径</th>
                <th data-options="field:'des',width:210">描述</th>
                <th data-options="field:'defstate',width:80,formatter:formatIsOpen">默认展开</th>
            </tr>
        </thead>
    </table>

    <div id="dlg" class="easyui-dialog hid" style="width:600px;height:430px;padding:10px 20px;"
            closed="true" buttons="#dlg-buttons">
        <div class="ftitle">系统菜单信息</div>
        <form id="fm" method="post" novalidate>
            <div class="fitem">
                <label>菜单名称:</label>
                <input name="menuname" type="text" class="easyui-textbox iw" data-options="required:true" missingMessage="请输入名称">
            </div>
            <div class="fitem">
                <label>菜单图标:</label>
                <input name="icon" type="text" class="easyui-textbox iw">
            </div>
            <div class="fitem">
                <label>上级:</label>
                <input class="easyui-combotree iw" id="parentId" name="parentId" data-options="url:'alltree',method:'get',required:true">
            </div>
            <div class="fitem">
                <label>访问路径:</label>
                <input name="url" type="text" data-options="multiline:true" style="height:68px;" class="easyui-textbox ltw">
            </div>
            <div class="fitem">
                <label>描述:</label>
                <input name="des" type="text" data-options="multiline:true" style="height:68px;" class="easyui-textbox ltw">
            </div>
        </form>
    </div>
    <div id="dlg-buttons" class="hid">
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="savesysMenu()">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
    </div>

</div>

<script type="text/javascript">
    // 页面加载
    $(document).ready(function() {
        $('#dg').treegrid({
            url: 'alltree',
            iconCls: 'icon-ok',
            toolbar: '#toolbar',
            collapsible: true,
            rownumbers: false,
            animate: true,
            fitColumns: true,
            singleSelect: true,
            loadMsg: '数据加载中请稍后……',
            method: 'get',
            idField: 'id',
            treeField: 'text'
        });
    });

    function updateState(menuid, defstate) {
        $('#id'+menuid+'').toggleClass("switchSmallOn");
        $.get('upstate?menuid='+menuid,{},function(result) {
            if (result.success){
                $('#dg').treegrid('reload');
            } else {
                $.messager.alert('错误！', result.msg);
            }
        },'json');
    }

    function formatIsOpen(value, rec) {
        if (value == 'closed') {
            return '<div id="id'+rec.menuid+'" class="switchSmall" style="margin-top:-1px;" plain="true" onclick="updateState('+rec.menuid+', \''+rec.defstate+'\');" />';
        } else if (value == 'open') {
            return '<div id="id'+rec.menuid+'" class="switchSmall switchSmallOn" style="margin-top:-1px;" plain="true" onclick="updateState('+rec.menuid+', \''+rec.defstate+'\');" />';
        } else {
            return '';
        }
    }

    function formatIsShow(value) {
        if (value) {
            return '是';
        } else {
            return '否';
        }
    }
    
    var url;
    function newsysMenu(){
        $('#dlg').dialog('open').dialog('setTitle', '添加系统菜单');
        $('#fm').form('clear');
        
        $('#parentId').combotree('setValue', 0);
        url = 'save';
    }
    
    function editsysMenu(){
    	var row = $('#dg').datagrid('getSelected');
        if (row){
            $('#dlg').dialog('open').dialog('setTitle','修改系统菜单');
            $('#fm').form('load',row);
            if (row.parentId) {
                $('#parentId').combotree('setValue', row.parentId);
            } else {
                $('#parentId').combotree('setValue', 0);
            }
            url = 'update?menuid='+row.menuid;
        }
    }
    
    function savesysMenu(){
        $('#fm').form('submit',{
            url: url,
            onSubmit: function(){
                return $(this).form('validate');
            },
            success: function(result){
                var result = eval('('+result+')');
                if (result.success){
                    $('#dlg').dialog('close');      // close the dialog
                    $('#dg').treegrid('reload');    // reload the sysMenu data
                } else {
                    $.messager.show({
                        title: 'Error',
                        msg: result.msg
                    });
                }
            }
        });
    }

    function removeItem(){
        var row = $('#dg').datagrid('getSelected');
        if (row){
            $.messager.confirm('删除','你确定要删除这条数据吗?',function(r){
                if (r){
                    $.post('delete',{menuid:row.menuid},function(result){
                        if (result.success){
                            $('#dg').treegrid('reload');    // reload the travelStrategy data
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