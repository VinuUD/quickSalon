document.getElementById("cancel").onclick=function(){
    document.getElementById("modal").style.display='none'
}

window.onclick = function(event) {
    if (event.target === document.getElementById("modal")) {
        document.getElementById("modal").style.display = "none";
    }
}
document.getElementById("confirm").onclick=function(){
    document.getElementById("modal").style.display='block'
}
