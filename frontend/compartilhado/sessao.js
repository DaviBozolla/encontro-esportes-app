function entrarComo(id, nome) {
    localStorage.setItem("usuarioId", id);
    localStorage.setItem("usuarioNome", nome);
}

function usuarioLogado() {
    const id = localStorage.getItem("usuarioId");

    if (id == null) {
        return null;
    }
    else {
        const usuario = {id: Number(id), nome: localStorage.getItem("usuarioNome")};
        return usuario;
    }
}

function sair() {
    localStorage.removeItem("usuarioId");
    localStorage.removeItem("usuarioNome");
}