<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <meta name="keywords" content="">
    <meta name="description" content="">
    <link rel="shortcut icon" href="${path}/resources/img/favicon.ico">
    <link rel="Bookmark" href="${path}/resources/img/favicon.ico">
    <title>首页 - 管理系统 ${wd-test}</title>

    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/fonts.googleapis.com.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

    <!--[if lte IE 9]>
      <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
    <![endif]-->
    <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/ace-skins.min.css" />
    <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/ace-rtl.min.css" />
    
    <link href="${path}/resources/css/tree.css" rel="stylesheet">
    <link href="${path}/resources/css/icheck/blue.css" rel="stylesheet">

    <!--[if lte IE 9]>
      <link rel="stylesheet" href="${path}/resources/js/ace-master/assets/css/ace-ie.min.css" />
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="${path}/resources/js/ace-master/assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="${path}/resources/js/ace-master/assets/js/html5shiv.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/respond.min.js"></script>
    <![endif]-->
    
    <style type="text/css">
    li>ol, li>ul {
      margin-left: 40px;
    }
    </style>
  </head>

  <body class="no-skin">

<jsp:include page="/WEB-INF/views/admin/common/nav.jsp"></jsp:include>

<div class="main-container ace-save-state" id="main-container">
      <script type="text/javascript">
        try{ace.settings.loadState('main-container')}catch(e){}
      </script>

<jsp:include page="/WEB-INF/views/admin/common/leftmenu.jsp"></jsp:include>
<div class="main-content">
  <div class="main-content-inner">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
      <ul class="breadcrumb">
        <li>
          <i class="ace-icon fa fa-desktop"></i>
          <a href="#">系统管理</a>
        </li>
        <li class="active">资源管理</li>
      </ul><!-- /.breadcrumb -->

      <div class="nav-search" id="nav-search">
        <form class="form-search">
          <span class="input-icon">
            <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
            <i class="ace-icon fa fa-search nav-search-icon"></i>
          </span>
        </form>
      </div><!-- /.nav-search -->
    </div>
    
    <div class="page-content">
      <div class="ace-settings-container" id="ace-settings-container">
        <div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
          <i class="ace-icon fa fa-cog bigger-130"></i>
        </div>

        <div class="ace-settings-box clearfix" id="ace-settings-box">
          <div class="pull-left width-50">
            <div class="ace-settings-item">
              <div class="pull-left">
                <select id="skin-colorpicker" class="hide">
                  <option data-skin="no-skin" value="#438EB9">#438EB9</option>
                  <option data-skin="skin-1" value="#222A2D">#222A2D</option>
                  <option data-skin="skin-2" value="#C6487E">#C6487E</option>
                  <option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
                </select>
              </div>
              <span>&nbsp; Choose Skin</span>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-navbar" autocomplete="off" />
              <label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-sidebar" autocomplete="off" />
              <label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-breadcrumbs" autocomplete="off" />
              <label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" autocomplete="off" />
              <label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-add-container" autocomplete="off" />
              <label class="lbl" for="ace-settings-add-container">
                Inside
                <b>.container</b>
              </label>
            </div>
          </div><!-- /.pull-left -->

          <div class="pull-left width-50">
            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" autocomplete="off" />
              <label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" autocomplete="off" />
              <label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
            </div>

            <div class="ace-settings-item">
              <input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" autocomplete="off" />
              <label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
            </div>
          </div><!-- /.pull-left -->
        </div><!-- /.ace-settings-box -->
      </div><!-- /.ace-settings-container -->

      <div class="page-header">
        <h1>
          系统管理
          <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            资源管理
          </small>
        </h1>
      </div><!-- /.page-header -->

      <div class="row">
        <div class="col-xs-12">
        
        <div id="sources-tree" class="tree well">
          
        </div>
        
        </div>
      </div>
    </div>
    
  </div>
</div>
<jsp:include page="/WEB-INF/views/admin/common/footer.jsp"></jsp:include>

      <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
      </a>
    </div><!-- /.main-container -->

    <!-- basic scripts -->

    <!--[if !IE]> -->
    <script src="${path}/resources/js/ace-master/assets/js/jquery-2.1.4.min.js"></script>

    <!-- <![endif]-->

    <!--[if IE]>
<script src="${path}/resources/js/ace-master/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
    <script type="text/javascript">
      if('ontouchstart' in document.documentElement) document.write("<script src='${path}/resources/js/ace-master/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
    </script>
    <script src="${path}/resources/js/ace-master/assets/js/bootstrap.min.js"></script>

    <!-- page specific plugin scripts -->

    <!--[if lte IE 8]>
      <script src="assets/js/excanvas.min.js"></script>
    <![endif]-->
    <script src="${path}/resources/js/ace-master/assets/js/jquery-ui.custom.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/jquery.ui.touch-punch.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/jquery.easypiechart.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/jquery.sparkline.index.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/jquery.flot.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/jquery.flot.pie.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/jquery.flot.resize.min.js"></script>

    <!-- ace scripts -->
    <script src="${path}/resources/js/ace-master/assets/js/ace-elements.min.js"></script>
    <script src="${path}/resources/js/ace-master/assets/js/ace.min.js"></script>
    
    <script src="${path}/resources/js/utils.js"></script>
    <script src="${path}/resources/css/icheck/icheck.js"></script>

    <!-- inline scripts related to this page -->
    <script type="text/javascript">
      var treeMenuHtml = 'blank';
      function initLeftMenu() {
        var leftMenuData = {};
        $.post("${path}/admin/sysMenu/all", {}, function(result) {
          if (isNotBlank(result)) {
            leftMenuData = JSON.parse(result); //由JSON字符串转换为JSON对象
            treeMenuHtml = new treeMenu(leftMenuData).init(0);
            $('#left-menu').append(treeMenuHtml);
            setActive();
          }
        });
        // alert($('#menu-test'));
      }
      
      function setActive() {
        $("li.active").removeClass("active");
        //$("#menuid_1 + ul").addClass("nav-show");
        $("#menuid_1").find("ul:eq(0)").show();
        $("[menucode='sysResource']").addClass("active");
      }
      
      function initSourcesTree() {
        var sourcesTreeHtml = 'blank';
        var sourcesTreeData = {};
        $.post("${path}/admin/sysMenu/all", {}, function(result) {
          if (isNotBlank(result)) {
            sourcesTreeData = JSON.parse(result); //由JSON字符串转换为JSON对象
            rootNode = {"menuid":0,"menuname":"系统资源","icon":"desktop","url":"","parentid":-1,"subids":"1,2","des":"","leaf":false,"defstate":"","weight":0,"deleted":false};
            
            sourcesTreeData.unshift(rootNode);
            // alert(sourcesTreeData.length);
            sourcesTreeHtml = '<ul>' +　new sourcesTreeMenu(sourcesTreeData).init(-1) + '</ul>';
            $('#sources-tree').append(sourcesTreeHtml);
            initResourcesTree();
          }
        });
        // alert($('#menu-test'));
      }
      
      jQuery(function($) {
        initLeftMenu();
        initSourcesTree();
      })
      
      function sourcesTreeMenu(a) {
        this.tree = a||[];
        this.groups = {};
      };
      sourcesTreeMenu.prototype = {
        init:function(pid) {
          this.group();
          return this.getDom(this.groups[pid]);
        },
        group:function() {
          for (var i = 0; i < this.tree.length; i++) {
            if (this.groups[this.tree[i].parentid]) {
              this.groups[this.tree[i].parentid].push(this.tree[i]);
            } else {
              this.groups[this.tree[i].parentid]=[];
              this.groups[this.tree[i].parentid].push(this.tree[i]);
            }
          }
        },
        getDom:function(a) {
          if (!a) { return '' }
          var html = '';
          for (var i = 0; i < a.length; i++) {
            if (a[i].leaf) {
              html += '\n<li id="menuid_' + a[i].menuid + '" style="display:none;" subids="' + a[i].subids + '" parentid="' + a[i].parentid + '" class="">\n';
              html += getSourcesMenuHtml(a[i].icon, a[i].menuname, false, a[i].url);
              html += '\n</li>\n';
            } else {
              //html += getSourcesMenuHtml(a[i].icon, a[i].menuname, true, '');
              var defStateHtml = '';
              if (a[i].defstate == 'closed') {
                defStateHtml = 'display: none;';
              } else {
                defStateHtml = 'display: list-item;';
              }
              html += '\n<li id="menuid_' + a[i].menuid + '">\n';
              html += getSourcesMenuHtml(a[i].icon, a[i].menuname, true, '');
              html += '<ul>';
              html += this.getDom(this.groups[a[i].menuid]);
              html += '</ul>';
              html += '\n</li>\n';
            }
          };
          return html;
        }
      };

      function getSourcesMenuHtml(iconName, menuName, havChildren, hrefStr) {
        if (havChildren) {
          return '<div class="space-node">&nbsp;</div><input type="checkbox" /> <span><i class="glyphicon glyphicon-folder-open"></i> ' + menuName + '</span>';
        } else {
          return '<div class="space-node">&nbsp;</div>所有&nbsp;<input id="1" type="checkbox" /><input id="2" type="checkbox" /> <span><i class="glyphicon glyphicon-leaf"></i> ' + menuName + '</span>';
        }
      }
      
      function initResourcesTree() {
        $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
        $('.tree li.parent_li > span').on('click', function(e) {
          var children = $(this).parent('li.parent_li').find(' > ul > li');
          if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('glyphicon glyphicon-folder-open');
          } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('glyphicon glyphicon-folder-open').removeClass('icon-plus-sign');
          }
          e.stopPropagation();
        });
        
        $('.tree input').iCheck({
          checkboxClass: 'icheckbox_square-blue',
          radioClass: 'iradio_square-blue',
          increaseArea: '20%' // optional
        });
        
        $('.tree input').on('ifChecked', function(event) {
          var subidsStr = $(this).parent().parent().attr("subids");
          if (subidsStr.length < 1) {
            $(this).parent().iCheck('check');
          } else {
            $(this).parent().parent().find("li").show();
            $(this).parent().parent().find("input").iCheck('check');
            // alert($(this).parent().parent().html());
            setParent($(this).parent().parent().parent());
          }
        });
        
        $('.tree input').on('ifUnchecked', function(event) {
          var subidsStr = $(this).parent().parent().attr("subids");
          if (subidsStr.length < 1) {
            $(this).parent().iCheck('uncheck');
          } else {
            $(this).parent().parent().find("input").iCheck('uncheck');
          }
        });
      }
      
      function setParent(pObj) {
        if (pObj[0].tagName == 'UL') {
          // pObj.siblings(":eq(1)").addClass("halfcheck");
          setParent(pObj.parent().parent());
        }
      }
      
      var treeDict = { "key1": "val1", "key2": "val2" };
      $.each(treeDict, function(index, key, val) {
        // alert(index);
      });
    </script>
  </body>
</html>