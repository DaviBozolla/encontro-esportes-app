async function carregarUsuarios() {
    const resposta = await fetch("http://localhost:8080/usuarios");
    const usuarios = await resposta.json();
    
    const listaUsuarios = document.getElementById("lista-usuarios");
    for (const usuario of usuarios) {
        const item = document.createElement("li");
        item.className = "card";
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

const formCadastro = document.getElementById("form-cadastro");
formCadastro.addEventListener("submit", async (evento) => {
    evento.preventDefault();

    const dados = Object.fromEntries(new FormData(formCadastro));
    if (dados.genero === "") {
        dados.genero = null;
    }

    const resposta = await fetch("http://localhost:8080/usuarios", {
        method: "POST",
        headers: { "Content-Type": "application/json"},
        body: JSON.stringify(dados)
    });

    const corpo = await resposta.json();

    for(const elemento of document.querySelectorAll(".erro")) {
        elemento.textContent = "";
    }

    if (resposta.ok) {
        entrarComo(corpo.id, corpo.nome);
        window.location.href = "inicio.html";
        return;
    }

    if (corpo.erro) {
        document.getElementById("erro-geral").textContent = corpo.erro
    } else {
        for (const campo in corpo) {
            document.getElementById("erro-" + campo).textContent = corpo[campo];  
        }
    }
})