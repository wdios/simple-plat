// 工具集合

// 字符串截取函数
function subStr(value, lenNum) {
  if (isNotBlank(value)) {
    return value.substring(0, lenNum);
  } else {
    return "";
  }
}

// 判空函数
function isNotBlank(variable) {
  if (variable === "" || variable === null || typeof variable === 'undefined') {
    return false;
  } else {
    if (variable.replace(/(^s*)|(s*$)/g, "").length == 0) {
      return false;
    } else {
      return true;
    }
  }
}

// 利用正则去掉前后空格
function spaceTrim(val) {
  return val.replace(/(^\s*)|(\s*$)/g, "");
}

// 获取传入时间跟当前时间间隔秒数
function GetMinDiff(startDate) {
  var startTime = new Date(Date.parse(startDate.replace(/-/g, "/"))).getTime();
  var endTime = new Date().getTime();
  var seconds = Math.abs((endTime - startTime)) / (1000);
  return parseInt(seconds);
}

// 获取当前月份天数
function mGetDate() {
  var date = new Date();
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var d = new Date(year, month, 0);
  return d.getDate();
}

// 生成随机字符串
function randomString(len) {
  len = len || 32;
　var $chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
  var maxPos = $chars.length;
　var pwd = '';
　for (i = 0; i < len; i++) {
    //0~32的整数
　　pwd += $chars.charAt(Math.floor(Math.random() * (maxPos + 1)));
　}
　return pwd;
}

function treeMenu(a) {
  this.tree = a||[];
  this.groups = {};
};
treeMenu.prototype = {
  init:function(pid) {
    this.group();
    return this.getDom(this.groups[pid]);
  },
  group:function() {
    for (var i = 0; i < this.tree.length; i++) {
      if (this.groups[this.tree[i].parentId]) {
        this.groups[this.tree[i].parentId].push(this.tree[i]);
      } else {
        this.groups[this.tree[i].parentId]=[];
        this.groups[this.tree[i].parentId].push(this.tree[i]);
      }
    }
  },
  getDom:function(a) {
    if (!a) { return '' }
    var html = '\n<ul >\n';
    for (var i = 0; i < a.length; i++) {
      html += '<li><a href="#">' + a[i].menuname + '</a>';
      html += this.getDom(this.groups[a[i].id]);
      html += '</li>\n';
    };
    html += '</ul>\n';
    return html;
  }
};
