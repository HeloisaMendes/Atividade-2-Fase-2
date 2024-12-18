let representanteId = null
let pjId = null

let estado = null;

let nomeRepresentante = null;
let enderecoRepresentante = null;
let telefoneRepresentante = null;
let remetenteDestinatarioRepresentante = null;
let cpfRepresentate = null;

// Esse método serve para adicionar mais de um número de telefone no campo telefone
const inputTelefoneEmpresa = document.getElementById("telefoneEmpresa");
const tagifyTelefone = new Tagify(inputTelefoneEmpresa, {
    pattern: /^\d+$/, // Apenas números
    delimiters: " ",  // Espaço como delimitador adicional, se necessário
});

// Essa parte formata o CPF
const cpfInputRepresentante = document.getElementById('cpfRepresentante');

cpfInputRepresentante.addEventListener('input', function () {
    let cpfRepresentante = cpfInputRepresentante.value;

    // Remove caracteres que não sejam números
    cpfRepresentante = cpfRepresentante.replace(/\D/g, '');

    // Limita a entrada a 11 dígitos
    cpfRepresentante = cpfRepresentante.substring(0, 11);

    // Adiciona a formatação do CPF
    if (cpfRepresentante.length > 9) {
        cpfRepresentante = cpfRepresentante.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, "$1.$2.$3-$4");
    } else if (cpfRepresentante.length > 6) {
        cpfRepresentante = cpfRepresentante.replace(/(\d{3})(\d{3})(\d{1,3})/, "$1.$2.$3");
    } else if (cpfRepresentante.length > 3) {
        cpfRepresentante = cpfRepresentante.replace(/(\d{3})(\d{1,3})/, "$1.$2");
    }

    // Atualiza o valor do campo com o CPF formatado
    cpfInputRepresentante.value = cpfRepresentante;

});


// Formatar CNPJ
const cnpjInput = document.getElementById('cnpj');

cnpjInput.addEventListener('input', function () {
    let cnpj = cnpjInput.value;

    // Remove caracteres que não sejam números
    cnpj = cnpj.replace(/\D/g, '');

    // Limita a entrada a 14 dígitos
    cnpj = cnpj.substring(0, 14);

    // Adiciona a formatação do CNPJ
    if (cnpj.length > 12) {
        cnpj = cnpj.replace(/(\d{2})(\d{3})(\d{3})(\d{4})(\d{2})/, "$1.$2.$3/$4-$5");
    } else if (cnpj.length > 8) {
        cnpj = cnpj.replace(/(\d{2})(\d{3})(\d{3})(\d{1,4})/, "$1.$2.$3/$4");
    } else if (cnpj.length > 5) {
        cnpj = cnpj.replace(/(\d{2})(\d{3})(\d{1,3})/, "$1.$2.$3");
    } else if (cnpj.length > 2) {
        cnpj = cnpj.replace(/(\d{2})(\d{1,3})/, "$1.$2");
    }

    // Atualiza o valor do campo com o CNPJ formatado
    cnpjInput.value = cnpj;
});


//Clicou na lupa
document.getElementById('lupaCPF').addEventListener('click', function() {

    const cpfRepresentanteFormatado = cpfInputRepresentante.value;

    if (!cpfRepresentanteFormatado) {

        alert('Por favor, insira um CPF para pesquisar.');
        cpfInputRepresentante.focus();
        return;
    }

    pesquisarCPFRepresentante(cpfRepresentanteFormatado)

});


//Função para pesquisar pelo CPF
function pesquisarCPFRepresentante(cpfRepresentanteFormatado){

    $.ajax({
        url: "http://localhost:" + porta + "/pessoaFisica/consultaPorCPF/" + cpfRepresentanteFormatado,
        type: 'GET', 
       
        success: function(data) {

            if(!data || Object.keys(data).length === 0) {
    
                alert('CPF não encontrado. Antes de cadastrar uma PJ, você deve cadastrar o representante como PF.');

                // Método na classe cliente.html
                trocarTelaManualmente();

                // Mudar o estado para adicionar
                //isUpdating = false;
        
            } else {

                
                // Se o CPF existir, ele pode cadastrar uma Pessoa Jurídica
                representanteId = data.id;

                nomeRepresentante = data.nomeCli;
                enderecoRepresentante = data.endereco;
                telefoneRepresentante = data.telefone;
                remetenteDestinatarioRepresentante = data.remetenteDestinatario;
                cpfRepresentate = data.cpf;

                alert("Insira o CNPJ");
                
                //Clicou na lupa
                document.getElementById('lupaCNPJ').addEventListener('click', function() {

                    const cnpjFormatado = cnpjInput.value;

                    if (!cnpjFormatado) {

                        alert('Por favor, insira um CNPJ para pesquisar.');
                        cnpjInput.focus();
                        return;
                    }

                    pesquisarCNPJ(cnpjFormatado)

                });

                // Mudar o estado para atualizar
                //isUpdating = true;
                
            }
        },
        error: function(msg){
            alert("Erro de busca... Consulta por CPF")
        }
    });
}


//Função para pesquisar pelo CNPJ
function pesquisarCNPJ(cnpjFormatado){

    const cnpjSemFormatacao = cnpjFormatado.replace(/\D/g, ''); // Remove pontos e hífen

    $.ajax({
        url: "http://localhost:" + porta + "/pessoaJuridica/consultaPorCNPJ/" + cnpjSemFormatacao,
        type: 'GET', 
       
        success: function(data) {

            if(!data || Object.keys(data).length === 0) {
    
                alert('CNPJ não encontrado. Você pode cadastrar uma nova PJ.');

                // Mudar o estado para adicionar
                estado = false;
        
            } else {

                // Se o CNPJ existir, preencha os campos com os dados retornados
                pjId = data.id;
                document.getElementById("cmbTipoCliente").selectedIndex = 1;
                $('#dataInscPJ').val(data.dataInsc);
                $('#nomeEmpresa').val(data.nomeEmpresa);
                $('#razaoSocial').val(data.razaoSocial);
                $('#inscricaoEstadual').val(data.inscEstadual);
                $('#enderecoEmpresa').val(data.enderecoEmpresa);
                $('#telefoneEmpresa').val(data.telefoneEmpresa);

                if(data.remetenteDestinatario === "remetente"){
                    document.getElementById("cmbRemetenteDestinatarioPJ").selectedIndex = 0;
                }else{
                    document.getElementById("cmbRemetenteDestinatarioPJ").selectedIndex = 1;
                }

                // Mudar o estado para atualizar
                estado = true;
            }
        },
        error: function(msg){
            alert("Erro de busca... Consulta por CNPJ")
          }
    });
}


document.getElementById('formularioPJ').addEventListener('submit', function(event) {
    event.preventDefault(); // Impede o envio do formulário

    // Obtém os valores dos campos do formulário
    const cpfRepresentante = document.getElementById('cpfRepresentante').value;
    const cnpj = document.getElementById('cnpj').value;
    const dataInscPJ = document.getElementById('dataInscPJ').value;
    const remetenteDestinatarioPJ = document.getElementById('cmbRemetenteDestinatarioPJ').value;
    const razaoSocial = document.getElementById('razaoSocial').value;
    const inscEstadual = document.getElementById('inscricaoEstadual').value;
    const nomeEmpresa = document.getElementById('nomeEmpresa').value;
    const enderecoEmpresa = document.getElementById('enderecoEmpresa').value;
    const telefoneEmpresa = document.getElementById('telefoneEmpresa').value;

    const cnpjSemFormatacao = cnpj.replace(/\D/g, ''); // Remove pontos e hífen

    pesquisarCPFRepresentante(cpfRepresentante);

    // Verifica se todos os campos estão preenchidos
    if (cnpjSemFormatacao && dataInscPJ && remetenteDestinatarioPJ && razaoSocial && inscEstadual && nomeEmpresa && enderecoEmpresa && telefoneEmpresa && representanteId) {

        if(estado != null){

            //testo se a variavel update está false ou true 
            if (estado) {
                // Se estamos atualizando, chama a função de atualização
                atualizarPJ(nomeRepresentante, enderecoRepresentante, telefoneRepresentante,
                cpfRepresentate, cnpjSemFormatacao, dataInscPJ, remetenteDestinatarioPJ, razaoSocial, inscEstadual, nomeEmpresa, 
                enderecoEmpresa, telefoneEmpresa, representanteId);


            } else {
                // Se não estamos atualizando, chama a função de adição
                adicionarPJ(nomeRepresentante, enderecoRepresentante, telefoneRepresentante,
                cpfRepresentate, cnpjSemFormatacao, dataInscPJ, remetenteDestinatarioPJ, razaoSocial, inscEstadual, nomeEmpresa, 
                enderecoEmpresa, telefoneEmpresa, representanteId);
            }

        } else {

            alert("Pesquise se o CNPJ já está cadastrado em nossa base de dados!")
            const cnpjInput = document.getElementById("cnpj");
            cnpjInput.focus();
        }
        
    } else {
        alert("Por favor, preencha todos os campos!");
    }
});


// Função para adicionar um novo cliente
function adicionarPJ(nomeRepresentante, enderecoRepresentante, telefoneRepresentante,
    cpfRepresentate, cnpjSemFormatacao, dataInscPJ, remetenteDestinatarioPJ, razaoSocial, inscEstadual, nomeEmpresa, 
    enderecoEmpresa, telefoneEmpresa, representanteId) {
    $.ajax({
        url: "http://localhost:" + porta + "/pessoaJuridica/inserir",
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({
            nomeCli: nomeRepresentante,
            endereco: enderecoRepresentante,
            telefone: telefoneRepresentante,
            cpf: cpfRepresentate,
            remetenteDestinatario: remetenteDestinatarioPJ,
            cnpj: cnpjSemFormatacao,
            dataInsc: dataInscPJ,
            razaoSocial: razaoSocial,
            inscEstadual: inscEstadual,
            nomeEmpresa: nomeEmpresa,
            enderecoEmpresa: enderecoEmpresa,
            telefoneEmpresa: telefoneEmpresa,
            representante: {id: representanteId}
        }),
        success: function(resposta) {

            //Preenche o id cliente, quando um novo cliente é criado
            pjId = resposta.id

            alert("Novo cliente adicionado com sucesso!");

            pjId = null;
            limpaTelaPJ()
        },
        error: function() {
            alert("Erro ao adicionar o novo cliente.");
            limpaTelaPJ()
        }
    });
}


// Função para atualizar os dados do cliente
function atualizarPJ(nomeRepresentante, enderecoRepresentante, telefoneRepresentante,
    cpfRepresentate, cnpjSemFormatacao, dataInscPJ, remetenteDestinatarioPJ, razaoSocial, inscEstadual, nomeEmpresa, 
    enderecoEmpresa, telefoneEmpresa, representanteId) {
    $.ajax({
        url: "http://localhost:" + porta + "/pessoaJuridica/atualizar", 
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            id: pjId,
            nomeCli: nomeRepresentante,
            endereco: enderecoRepresentante,
            telefone: telefoneRepresentante,
            cpf: cpfRepresentate,
            remetenteDestinatario: remetenteDestinatarioPJ,
            cnpj: cnpjSemFormatacao,
            dataInsc: dataInscPJ,
            razaoSocial: razaoSocial,
            inscEstadual: inscEstadual,
            nomeEmpresa: nomeEmpresa,
            enderecoEmpresa: enderecoEmpresa,
            telefoneEmpresa: telefoneEmpresa,
            representante: {id: representanteId}
        }),
        success: function() {
            alert("Dados do cliente atualizados com sucesso!");
            limpaTelaPJ()
        },
        error: function() {
            alert("Erro ao atualizar os dados do cliente.");
            limpaTelaPJ()
        }
    });
}

function excluirPJ() {

    if (pjId != null) { 

        // Quer dizer que o cliente já foi pesquisado
        $.ajax({
            url :  "http://localhost:" + porta + "/pessoaJuridica/excluir/" + pjId,
            type: "DELETE",
    
    
            success: function(msg){
        
            alert("Cliente excluído com sucesso!")
        
                limpaTelaPJ();
                pjId = null;
                
            },
            error: function(msg) {
                alert("Erro de exclusão...")
            }
            
        });
        
    } else {
        alert("Você deve pesquisar o CPF e o CNPJ para depois excluir");
        const cpfInputRepresentante = document.getElementById("cpfRepresentante");
        const cnpjInput = document.getElementById("cnpj");
        cpfInputRepresentante.focus();
        cnpjInput.focus();
    }
    
}

function limpaTelaPJ() {

    // Define todos os campos de entrada como vazios
    document.getElementById("cmbTipoCliente").selectedIndex = 1;
    document.getElementById('cpfRepresentante').value = '';
    document.getElementById('cnpj').value = '';
    document.getElementById('dataInscPJ').value = '';
    document.getElementById("cmbRemetenteDestinatarioPJ").selectedIndex = 0;
    document.getElementById('razaoSocial').value = '';
    document.getElementById('inscricaoEstadual').value = '';
    document.getElementById('nomeEmpresa').value = '';
    document.getElementById('enderecoEmpresa').value = '';
    document.getElementById('telefoneEmpresa').value = '';
    
    //define isUpdating como null novamente
    estado = null;

}