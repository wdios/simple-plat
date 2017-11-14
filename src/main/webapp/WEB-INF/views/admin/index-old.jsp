<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="${path}/resources/img/favicon.ico">
    <link rel="Bookmark" href="${path}/resources/img/favicon.ico">
    <title>管理系统</title>
    <link rel="stylesheet" type="text/css" href="${path}/resources/js/easyui1.5.2/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/js/easyui1.5.2/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${path}/resources/js/easyui1.5.2/themes/default.css">
    <script type="text/javascript" src="${path}/resources/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${path}/resources/js/easyui1.5.2/jquery.easyui.min.js"></script>

<script type="text/javascript">
var _menus = ${menus};
var selectedPanelname;
function showcontent(url) {
    var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
    $('#content').html(content);
}

$(function() {
    // 导航菜单绑定初始化
    $("#wnav").accordion({
        animate : false
    });
    
    addNav(_menus);
    
    // clearNav();

    // 绑定tabs的右键菜单
    $("#tabs").tabs({
        onContextMenu:function(e,title) {
            e.preventDefault();
            $('#tabsMenu').menu('show', {
                left: e.pageX,
                top: e.pageY
            }).data("tabTitle", title);
        }
    });

    // 实例化menu的onClick事件
    $("#tabsMenu").menu({
        onClick:function(item) {
            closeTab(this,item.name);
        }
    });

    openTab('用户管理', 'sysUser/view');
});

// 在右边center区域打开菜单，新增tab
function openTab(text, url) {
    if ($("#tabs").tabs('exists', text)) {
        $('#tabs').tabs('select', text);
    } else {
        var content = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
        $('#tabs').tabs('add', {
            title:text,
            closable:true,
            content:content
        });
    }
}

// 几个关闭事件的实现
function closeTab(menu,type) {
    var curTabTitle = $(menu).data("tabTitle");
    var tabs = $("#tabs");

    if(type === "close") {
        tabs.tabs("close", curTabTitle);
    } else {
		var allTabs = tabs.tabs("tabs");
		var closeTabsTitle = [];

		$.each(allTabs, function() {
			var opt = $(this).panel("options");
			if (opt.closable && type === "Other") {
				if (opt.title != curTabTitle) {
					closeTabsTitle.push(opt.title);
				} else {
					tabs.tabs("select", 1);
				}
			} else if (opt.closable && type === "All") {
				closeTabsTitle.push(opt.title);
			}
		});

		for (var i = 0; i < closeTabsTitle.length; i++) {
			tabs.tabs("close", closeTabsTitle[i]);
		}
	}
}

// 菜单项鼠标Hover
function hoverMenuItem() {
    $(".easyui-accordion").find('a').hover(function() {
        $(this).parent().addClass("hover");
    }, function() {
        $(this).parent().removeClass("hover");
    });
}

// 添加
function addNav(data) {
    $.each(data, function(i, sm) {
        var menulist = "";
        menulist += '<ul class="navlist">';
        $.each(sm.children, function(j, o) {
            menulist += '<li><div ><a ref="' + o.id + '" href="javascript:void(0)" onclick="openTab(\''
                + o.text + '\',\'' + o.url + '\')"><span class="icon ' + o.icon + '" >&nbsp;</span><span class="nav">' + o.text + '</span></a></div>';
            if (o.children && o.children.length > 0) {
                //li.find('div').addClass('icon-arrow');
                menulist += '<ul class="third_ul">';
                $.each(o.children, function(k, p) {
                    menulist += '<li><div><a ref="' + p.id + '" href="javascript:void(0)" onclick="showcontent(\''
                + p.url + '\')"><span class="icon ' + p.icon + '" >&nbsp;</span><span class="nav">' + p.text + '</span></a></div></li>'
                });
                menulist += '</ul>';
            }
            menulist += '</li>';
        });
        menulist += '</ul>';

        $('#wnav').accordion('add', {
            title : sm.text,
            content : menulist,
            border : false,
            iconCls : 'icon ' + sm.icon
        });

        if(i == 0)
            selectedPanelname = sm.text;
    });
    $('#wnav').accordion('select', selectedPanelname);

    var pp = $('#wnav').accordion('panels');
    var t = pp[0].panel('options').title;
    $('#wnav').accordion('select', t);
}

// 清空
function clearNav() {
    var pp = $('#wnav').accordion('panels');
    $.each(pp, function(i, n) {
        if (n) {
            var t = n.panel('options').title;
            $('#wnav').accordion('remove', t);
        }
    });
    pp = $('#wnav').accordion('getSelected');
    if (pp) {
        var title = pp.panel('options').title;
        $('#wnav').accordion('remove', title);
    }
}

function addTab(subtitle, url, icon) {
    if (!$('#tabs').tabs('exists', subtitle)) {
        $('#tabs').tabs('add', {
            title : subtitle,
            content : createFrame(url),
            closable : true,
            icon : icon
        });
    } else {
        $('#tabs').tabs('select', subtitle);
        $('#mm-tabupdate').click();
    }
    tabClose();
}

</script>

<style>
.footer { width: 100%; text-align: center; line-height: 35px; }
.top-bg { background-color: #d8e4fe; height: 80px; }
</style>

</head>
<body class="easyui-layout">
    <div region="north" border="true" split="true" style="overflow: hidden;">
        <div class="top-bg"><a style="position:relative;top:20px;right:20px;float:right;font: 12px/18px Verdana, Geneva, sans-serif;" href="logout">退出系统</a></div>
    </div>

    <div region="south" border="true" split="true" style="overflow: hidden; height: 40px;">
        <div class="footer">版权所有：<a href=""></a></div>
    </div>

    <div region="west" split="true" title="导航菜单" style="width: 200px;">
        <div id='wnav' class="easyui-accordion">
            <!--  导航内容 -->
        </div>
    </div>

    <div id="content" region="center" style="overflow: hidden;">
        <div class="easyui-tabs" fit="true" border="false" id="tabs">
          <div title="首页"></div>
        </div>
    </div>

    <div id="tabsMenu" class="easyui-menu" style="width:120px;">
        <div name="close">关闭</div>
        <div name="Other">关闭其他</div>
        <div name="All">关闭所有</div>
    </div>

</body>
</html>