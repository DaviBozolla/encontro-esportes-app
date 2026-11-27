async function carregarUsuarios() {
    const resposta = await fetch("http://localhost:8080/usuarios");
    const usuarios = await resposta.json();
    
    const listaUsuarios = document.getElementById("lista-usuarios");
    for (const usuario of usuarios) {
        const item = document.createElement("li");
        item.textContent = usuario.nome;
        const botao = document.createElement("button");
        botao.textContent = "Entrar";
        botao.addEventListener("click", () => {
            entrarComo(usuario.id, usuario.nome);
            window.location.href = "inicio.html";
        });
        item.appendChild(botao);
        listaUsuarios.appendChild(item);
    }
}

carregarUsuarios();