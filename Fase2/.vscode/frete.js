porta = 2424

let idFrete = null
let idEstadoOrigem = null
let idEstadoDestino = null
let idCidadeOrigem = null
let idCidadeDestino = null
let idDestinatario = null
let idRemetente = null
let idFuncionario = null
let valorTotal = null
let valorMercadoria = null

let isUpdating = null;

//Clicou na lupa
document.getElementById('lupa').addEventListener('click', function() {

    const numeroInput = document.getElementById("numeroFrete");
    const numeroFrete = numeroInput.value;

    if (!numeroFrete) {

        alert('Por favor, insira o número de um frete para pesquisar.');
        numeroInput.focus();
        return;
    }

    pesquisarNumeroFrete(numeroFrete)

});

//Função para pesquisar pelo UF
function pesquisarNumeroFrete(numeroFrete){
    $.ajax({
        url: "http://localhost:" + porta + "/frete/consultaPorId/" + numeroFrete,
        type: 'GET', 
       
        success: function(data) {

            if(!data || Object.keys(data).length === 0) {
    
                alert('Frete não encontrado. Você pode cadastrar um novo frete.');
                // Mudar o estado para adicionar
                isUpdating = false;
        
            } else {

                // Se o frete existir, preencha os campos com os dados retornados
                idFrete = data.id;

                
                $('#numeroFrete').val(data.id);
                if(data.quemPaga === "remetente"){
                    document.getElementById("cmbQuemPaga").selectedIndex = 0;
                }else{
                    document.getElementById("cmbQuemPaga").selectedIndex = 1;
                }

                if(data.pesoOuValor === "peso"){
                    document.getElementById("cmbTipoCalculo").selectedIndex = 0;
                }else{
                    document.getElementById("cmbTipoCalculo").selectedIndex = 1;
                }

                $('#pesoUnitario').val(data.peso);
                $('#valorUnitario').val(data.valor);
                $('#valorPedagio').val(data.pedagio);
                $('#dataFrete').val(data.dataFrete);
                $('#icms').val(data.icms);
                
                // falta fazer

                // Mudar o estado para atualizar
                isUpdating = true;
            }
        },
        error: function(msg){
            alert("Erro de busca... Consulta por UF")
          }
    });
}


document.getElementById('formulario').addEventListener('submit', function(event) {
    event.preventDefault(); // Impede o envio do formulário

    // Obtém os valores dos campos do formulário
    const quemPaga = document.getElementById('cmbQuemPaga').value;
    const tipoCalculo = document.getElementById('cmbTipoCalculo').value;
    const pesoMercadoria = document.getElementById('pesoUnitario').value;
    const valorMercadoria = document.getElementById('valorUnitario').value;
    const valorPedagio = document.getElementById('valorPedagio').value;
    const dataFrete = document.getElementById('dataFrete').value;
    const icms = document.getElementById('icms').value;
    const estadoOrigem = document.getElementById('cmbEstadoOrigem').value;
    const cidadeOrigem = document.getElementById('cmbCidadeOrigem').value;
    const estadoDestino = document.getElementById('cmbEstadoDestino').value;
    const cidadeDestino = document.getElementById('cmbCidadeDestino').value;
    const destinatario = document.getElementById('cmbDestinatario').value;
    const remetente = document.getElementById('cmbRemetente').value;
    const funcionario = document.getElementById('cmbFuncionario').value;

    // Verifica se todos os campos estão preenchidos
    if (quemPaga && tipoCalculo && pesoMercadoria && valorMercadoria && valorPedagio && 
        dataFrete && icms && estadoOrigem && cidadeOrigem && estadoDestino && cidadeDestino && destinatario && remetente && funcionario) {

        if(isUpdating != null){

            //testo se a variavel update está false ou true 
            if (isUpdating) {
                // Se estamos atualizando, chama a função de atualização
                atualizar(estado, nomeCidade, pesoUnitario, valorUnitario);

            } else {
                // Se não estamos atualizando, chama a função de adição
                adicionar(estado, nomeCidade, pesoUnitario, valorUnitario);
            }

        } else {

            alert("Pesquise se essa cidade já está cadastrada em nossa base de dados!")
            const cidadeInput = document.getElementById("nome");
            cidadeInput.focus();
        }
        
    } else {
        alert("Por favor, preencha todos os campos!");
    }
});
