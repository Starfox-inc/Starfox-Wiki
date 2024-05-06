
if (typeof localStorage.getItem("game") == 'undefined') {
    localStorage.setItem("game", "lol");
}
let game = localStorage.getItem("game");
console.log(game);
$('#' + game).toggleClass('gameselected');
let gamebar = document.querySelector(".gamebar");
let gamebarItems = gamebar.querySelectorAll("li");
gamebarItems.forEach(item => {
    item.addEventListener("click", () => {
        $('#' + game).toggleClass('gameselected');
        game = item.querySelector("img").alt;
        console.log(game);
        localStorage.setItem("game", game);
        $('#' + game).toggleClass('gameselected');
        let lastSlashIndex = this.location.href.lastIndexOf('/');
        let newLocation = this.location.href.substring(0, lastSlashIndex) + "/" + game;
        window.location.href = newLocation;
    });
});
if(!window.location.href.includes(game) && (window.location.href.includes("matches") || window.location.href.includes("teams"))){
    window.location.href = this.location.href + "/" + game;
}
    
