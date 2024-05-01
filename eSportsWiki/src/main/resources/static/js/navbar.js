
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
    });
});
