var porta = 2424
let clienteId = null

let isUpdating = null;


// Esse método serve para adicionar mais de um número de telefone no campo telefone
const inputTelefone = document.getElementById("telefone");
const tagify = new Tagify(inputTelefone, {
    pattern: /^\d+$/, // Apenas números
    delimiters: " ",  // Espaço como delimitador adicional, se necessário
});


// Essa parte formata o CPF
const cpfInput = document.getElementById('cpf');

cpfInput.addEventListener('input', function () {
    let cpf = cpfInput.value;

    // Remove caracteres que não sejam números
    cpf = cpf.replace(/\D/g, '');

    // Limita a entrada a 11 dígitos
    cpf = cpf.substring(0, 11);

    // Adiciona a formatação do CPF
    if (cpf.length > 9) {
        cpf = cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
    } else if (cpf.length > 6) {
        cpf = cpf.replace(/(\d{3})(\d{3})(\d{1,3})/, "$1.$2.$3");
    } else if (cpf.length > 3) {
        cpf = cpf.replace(/(\d{3})(\d{1,3})/, "$1.$2");
    }

    // Atualiza o valor do campo com o CPF formatado
    cpfInput.value = cpf;

});


//Clicou na lupa
document.getElementById('lupa').addEventListener('click', function() {

    const cpfFormatado = cpfInput.value;

    if (!cpfFormatado) {

        alert('Por favor, insira um CPF para pesquisar.');
        cpfInput.focus();
        return;
    }

    pesquisarCPF(cpfFormatado)

});


//Função para pesquisar pelo CPF
function pesquisarCPF(cpfFormatado){
    $.ajax({
        url: "http://localhost:" + porta + "/pessoaFisica/consultaPorCPF/" + cpfFormatado,
        type: 'GET', 
       
        success: function(data) {

            if(!data || Object.keys(data).length === 0) {
    
                alert('CPF não encontrado. Você pode cadastrar um novo cliente.');
                // Mudar o estado para adicionar
                isUpdating = false;
        
            } else {

                // Se o CPF existir, preencha os campos com os dados retornados
                clienteId = data.id;
                document.getElementById("cmbTipoCliente").selectedIndex = 0;
                $('#dataInsc').val(data.dataInsc);
                $('#nome').val(data.nomeCli);
                $('#endereco').val(data.endereco);
                $('#telefone').val(data.telefone);

                if(data.remetenteDestinatario === "remetente"){
                    document.getElementById("cmbRemetenteDestinatario").selectedIndex = 0;
                }else{
                    document.getElementById("cmbRemetenteDestinatario").selectedIndex = 1;
                }

                // Mudar o estado para atualizar
                isUpdating = true;
            }
        },
        error: function(msg){
            alert("Erro de busca... Consulta por CPF")
          }
    });
}


document.getElementById('formularioPF').addEventListener('submit', function(event) {
    event.preventDefault(); // Impede o envio do formulário

    // Obtém os valores dos campos do formulário
    const cpf = document.getElementById('cpf').value;
    const dataInsc = document.getElementById('dataInsc').value;
    const remetenteDestinatario = document.getElementById('cmbRemetenteDestinatario').value;
    const nome = document.getElementById('nome').value;
    const endereco = document.getElementById('endereco').value;
    const telefone = document.getElementById('telefone').value;

    // Verifica se todos os campos estão preenchidos
    if (cpf && dataInsc && remetenteDestinatario && nome && endereco && telefone) {

        if(isUpdating != null){

            //testo se a variavel update está false ou true 
            if (isUpdating) {
                // Se estamos atualizando, chama a função de atualização
                atualizar(cpf, dataInsc, remetenteDestinatario, nome, endereco, telefone);

            } else {
                // Se não estamos atualizando, chama a função de adição
                adicionar(cpf, dataInsc, remetenteDestinatario, nome, endereco, telefone);
            }

        } else {

            alert("Pesquise se o CPF já está cadastrado em nossa base de dados!")
            const cpfInput = document.getElementById("cpf");
            cpfInput.focus();
        }
        
    } else {
        alert("Por favor, preencha todos os campos!");
    }
});


// Função para adicionar um novo cliente
function adicionar(cpf, dataInsc, remetenteDestinatario, nome, endereco, telefone) {
    $.ajax({
        url: "http://localhost:" + porta + "/pessoaFisica/inserir",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            nomeCli: nome,
            dataInsc: dataInsc,
            endereco: endereco,
            telefone: telefone,
            remetenteDestinatario: remetenteDestinatario,
            cpf: cpf
        }),
        success: function(resposta) {

            //Preenche o id cliente, quando um novo cliente é criado
            clienteId = resposta.id

            alert("Novo cliente adicionado com sucesso!");

            clienteId = null;
            limpaTela()
        },
        error: function() {
            alert("Erro ao adicionar o novo cliente.");
            limpaTela()
        }
    });
}


// Função para atualizar os dados do cliente
function atualizar(cpf, dataInsc, remetenteDestinatario, nome, endereco, telefone) {
    $.ajax({
        url: "http://localhost:" + porta + "/pessoaFisica/atualizar", 
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            id: clienteId,
            nomeCli: nome,
            dataInsc: dataInsc,
            endereco: endereco,
            telefone: telefone,
            remetenteDestinatario: remetenteDestinatario,
            cpf: cpf
        }),
        success: function() {
            alert("Dados do cliente atualizados com sucesso!");
            limpaTela()
        },
        error: function() {
            alert("Erro ao atualizar os dados do cliente.");
            limpaTela()
        }
    });
}

function excluirCliente() {

    if (clienteId != null) { 

        // Quer dizer que o cliente já foi pesquisado
        $.ajax({
            url :  "http://localhost:" + porta + "/pessoaFisica/excluir/" + clienteId,
            type: "DELETE",
    
    
            success: function(msg){
        
            alert("Cliente excluído com sucesso!")
        
                limpaTela();
                clienteId = null;
                
            },
            error: function(msg) {
                alert("Erro de exclusão...")
            }
            
        });
        
    } else {
        alert("Você deve pesquisar o CPF para depois excluir");
        const cpfInput = document.getElementById("cpf");
        cpfInput.focus();
    }
    
}

function limpaTela() {

    // Define todos os campos de entrada como vazios
    document.getElementById("cmbTipoCliente").selectedIndex = 0;
    document.getElementById('cpf').value = '';
    document.getElementById('dataInsc').value = '';
    document.getElementById("cmbRemetenteDestinatario").selectedIndex = 0;
    document.getElementById('nome').value = '';
    document.getElementById('endereco').value = '';
    document.getElementById('telefone').value = '';
    
    //define isUpdating como null novamente
    isUpdating = null;

}


