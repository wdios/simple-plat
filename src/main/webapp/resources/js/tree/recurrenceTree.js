// 遍历单个节点
function traverseNode(node){
    var divObj = document.getElementById("app");
    divObj.innerHTML = divObj.innerHTML + "  " + node.name;
}

// 递归遍历树
function traverseTree(node) {
    if (!node) {
        return;
    }

    traverseNode(node);
    if (node.children && node.children.length > 0) {
        var i = 0;
        for (i = 0; i < node.children.length; i++) {
            this.traverseTree(node.children[i]);
        }
    }
}

traverseTree(root);