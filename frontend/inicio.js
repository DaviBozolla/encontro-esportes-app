const usuario = usuarioLogado();

if (usuario == null) {
    window.location.href = "index.html"
}
else {
    const paragrafo = document.getElementById("boas-vindas");
    paragrafo.textContent = "Olá, " + usuario.nome + "!";

    const botao = document.getElementById("botao-sair");
    botao.addEventListener("click", () => {
        sair();
        window.location.href = "index.html"
    });
}
