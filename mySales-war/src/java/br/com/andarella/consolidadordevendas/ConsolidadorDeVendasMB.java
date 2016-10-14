package br.com.andarella.consolidadordevendas;

import br.com.andarella.dao.ConsolidadorDeVendasDAO;
import br.com.domain.ConsolidadorDeVendas;
import java.math.BigDecimal;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.data.FilterEvent;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;


@ManagedBean(name="consolidadorDeVendasMB")
@ViewScoped
public class ConsolidadorDeVendasMB {

    @EJB
    private ConsolidadorDeVendasDAO consolidadorDeVendasDAO;
    
    
    private ConsolidadorDeVendas consolidadorDeVendas = new ConsolidadorDeVendas();
    
    private List<ConsolidadorDeVendas> myListFiliais;
    
    private List<ConsolidadorDeVendas> myQuery;
    private List<ConsolidadorDeVendas> listaGraficoPercentualVendas;
    
    private PieChartModel graficoPercentualDeVendas = new PieChartModel();
    
    private List<ConsolidadorDeVendas> listaMovimentacoesDiarias;
    
    private PieChartModel graficoPercentualDeLinhaProduto = new PieChartModel();
    private List<ConsolidadorDeVendas> listaGraficoPercentualDeLinhaProduto;
    
    
    // Usado da dataTable de movimentações de vendedores por grupo de produtos
    private List<ConsolidadorDeVendas> listaMovDeGrupoDeProdutos;
    private List<ConsolidadorDeVendas> listaTotaisDeGrupoDeProdutos;
    
    
    //Usado para lista os vendedores
    private List<ConsolidadorDeVendas> listaDeVendedores;

    
    //Usado para listar as vendas por categorias e grade, incluindo estoque e giro
    private List<ConsolidadorDeVendas> listaDeVendasPorCategorias;

    
    // usado para exibir as datas formatadas para exibição
     private String dataFormatadaPeriodo;
    
    
    // Usado para filtros de datatable
    private List<ConsolidadorDeVendas> filteredConsolidadorDeVendas;
    
    //usado para obter a data atual do sistema
    private Date dataAtualVendas = new Date();
    
    private int anoAtualVendas;
       
    private String titulo;
        
   
    //usado grafico grade media
    private BarChartModel graficoGradeMedia = new BarChartModel();
    private List<ConsolidadorDeVendas> listaGraficoGradeMedia;
    
    
    public ConsolidadorDeVendasMB() {
    }
    
    private String mesAtualVendas;
    private String mesAtualVendasPorExtenso;
  
    private String filialAtualVendas;
    
    
    //totais de vendas diarias por filial e dia
    private List<ConsolidadorDeVendas> listaDeTotaisDeVendasDiarias;
    
    
    private int movDiariasCount;
    
    private int totaisMovDiariasCount;
    
    private List<ConsolidadorDeVendas> listaDeTotaisDeVendedoresCategorias;
    
    
    
    
    @PostConstruct
    public void init() {
        //Movimentações Filiais
        myQuery = obterListaConsolidadorDeVendasInicial();
        
        //Percentual de vendas filial
        criarGraficos();
        
        //Movimentações Diarias
        listaMovimentacoesDiarias = obterListaMovimentacoesDiariasInicial();
        //Tamanho da lista para setar o uso dos subtotais
        totaisMovDiariasCount = listaMovimentacoesDiarias.size()-1;
        
        
        
        //Movimentações de Grupo de Produtos
        listaMovDeGrupoDeProdutos = obterListaMovimentacoesDeGrupoDeProdutosInicial();
        
        //Criar os Totais de DataTable de Grupos por Produtos
        gerarTotaisPorTipo("1");
        
        //obtem lista inicial de vendedores
        listaDeVendedores = obterListadeVendedoresCategoriasInicial();
        
        //obtem lista inicial de vendas por categorias
        listaDeVendasPorCategorias = obterListaVendasPorCategoriasInicial();
        
        //Obter Data Formatada
        dataFormatadaPeriodo = obterPeriodoDatasFormatadas("1");
        
        //Obtendo ano atual do sistema
        Date data = new Date();
        GregorianCalendar dataCalIni = new GregorianCalendar();
        dataCalIni.setTime(data);
        anoAtualVendas = dataCalIni.get(Calendar.YEAR);
        
        //obter titulo apos a consulta
        titulo = obterTituloParametro();
        
        //OBter Mes Atual usado como valor padrão nos botoes
        mesAtualVendas = obterMesAtualPadrao();
       
        //Obter Filial Atual Padrao usado no titulo
        filialAtualVendas = obterFilialDaConsulta();
        
        //Obter Mes Atual Por extenso usado no titulo
        mesAtualVendasPorExtenso = obterMesAtualPadraoPorExtenso();
        
        
        //carrega a lista de vendedoras e categorias
       // listaDeTotaisDeVendedoresCategorias = obterListadeVendedoresCategorias();
       
        
    
    
        
        
    }
    
    public String obterMesAtualPadrao(){
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
	String mesAtual = Integer.toString(month);
             
        return mesAtual;
    }
    
    public String obterMesAtualPadraoPorExtenso(){
        
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
	               
        String mesAtualPorExtenso = obterMesPorExtenso(month);
        
        return mesAtualPorExtenso;
        
        
    }
    
    
    public void criarGraficos(){
        gerarGraficoPercentualDeVendas();
    }
    public void gerarGraficoPercentualDeVendas(){
        Date data = new Date();
        GregorianCalendar dataCalIni = new GregorianCalendar();
        GregorianCalendar dataCalFim = new GregorianCalendar();
        
        dataCalIni.setTime(data);
        dataCalFim.setTime(data);
        
        int diaIni = dataCalIni.get(Calendar.DAY_OF_MONTH);
        int diaFim = dataCalIni.get(Calendar.DAY_OF_MONTH)-1;
        
        dataCalIni.set(Calendar.DAY_OF_MONTH, 1);
        dataCalFim.set(Calendar.DAY_OF_MONTH, diaFim);
       
        SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
        String dataVendaInicial = out.format(dataCalIni.getTime()); 
        String dataVendaFinal   = out.format(dataCalFim.getTime()); 
        
        graficoPercentualDeVendas = new PieChartModel();
        listaGraficoPercentualVendas = consolidadorDeVendasDAO.getGraficoDePercentualdeVendasStart(dataVendaInicial,dataVendaFinal);

        if (listaGraficoPercentualVendas.size()==1){
            for(ConsolidadorDeVendas ConsolidadorDeVendas : listaGraficoPercentualVendas){
                graficoPercentualDeVendas.set(ConsolidadorDeVendas.getSiglaFilial()+" ", ConsolidadorDeVendas.getPercentual_vendas());
            }
            graficoPercentualDeVendas.set("Rede", 100.00);
        }else{
            for(ConsolidadorDeVendas ConsolidadorDeVendas : listaGraficoPercentualVendas){
                graficoPercentualDeVendas.set(ConsolidadorDeVendas.getSiglaFilial(), ConsolidadorDeVendas.getPercentual_vendas());
                
            } 
        }
        
        graficoPercentualDeVendas.setTitle("% Venda");
        graficoPercentualDeVendas.setShowDataLabels(true);
        graficoPercentualDeVendas.setDiameter(150);
        graficoPercentualDeVendas.setFill(true);
        graficoPercentualDeVendas.setLegendCols(1);
        graficoPercentualDeVendas.setLegendPosition("w"); 
        
        // Grafico Percentual de LinhaProduto
        graficoPercentualDeLinhaProduto = new PieChartModel();
        listaGraficoPercentualDeLinhaProduto = consolidadorDeVendasDAO.getGraficoPercentualPorLinhaProdutoStart(dataVendaInicial,dataVendaFinal);
        
           
        for(ConsolidadorDeVendas ConsolidadorDeVendas : listaGraficoPercentualDeLinhaProduto){
            graficoPercentualDeLinhaProduto.set(ConsolidadorDeVendas.getLinha(), ConsolidadorDeVendas.getPercentual());
        } 
       
        graficoPercentualDeLinhaProduto.setTitle("% Venda");
        graficoPercentualDeLinhaProduto.setShowDataLabels(true);
        graficoPercentualDeLinhaProduto.setDiameter(150);
        graficoPercentualDeLinhaProduto.setFill(true);
        graficoPercentualDeLinhaProduto.setLegendCols(1);
        //graficoPercentualDeLinhaProduto.setLegendRows(2);
        graficoPercentualDeLinhaProduto.setLegendPosition("w"); 
        
         //Grafico de Grade media
        graficoGradeMedia = new BarChartModel();
        ChartSeries calcados = new ChartSeries();
        calcados.setLabel("Calçados");
        int maxQtdeVendida = 0;
        listaGraficoGradeMedia = consolidadorDeVendasDAO.getGraficoGradeMediaStart(dataVendaInicial,dataVendaFinal);
        for(ConsolidadorDeVendas ConsolidadorDeVendas : listaGraficoGradeMedia){
            maxQtdeVendida = ConsolidadorDeVendas.getQtde_vendida();
            calcados.set(ConsolidadorDeVendas.getTamanho(), ConsolidadorDeVendas.getQtdeLiquida());
        } 
        
        
       // maxQtdeVendida = gerarMaximaQuantidadeGradeMedia(maxQtdeVendida);
        maxQtdeVendida += maxQtdeVendida;
        
        graficoGradeMedia.addSeries(calcados);
        graficoGradeMedia.setTitle("Grade Média");
        graficoGradeMedia.setAnimate(true);
       // graficoGradeMedia.setLegendPosition("ne");
        Axis yAxis = graficoGradeMedia.getAxis(AxisType.Y);
        yAxis.setTickFormat("%d");
        yAxis.setMin(0);
        yAxis.setMax(maxQtdeVendida);
        
        
    }
    
    
    public List<ConsolidadorDeVendas> obterListaConsolidadorDeVendasInicial(){
        Date data = new Date();
        GregorianCalendar dataCalIni = new GregorianCalendar();
        GregorianCalendar dataCalFim = new GregorianCalendar();
        
        dataCalIni.setTime(data);
        dataCalFim.setTime(data);
        
        int diaIni = dataCalIni.get(Calendar.DAY_OF_MONTH);
        int diaFim = dataCalIni.get(Calendar.DAY_OF_MONTH)-1;
        
        dataCalIni.set(Calendar.DAY_OF_MONTH, 1);
        dataCalFim.set(Calendar.DAY_OF_MONTH, diaFim);
       
        SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
        String dataVendaInicial = out.format(dataCalIni.getTime()); 
        String dataVendaFinal   = out.format(dataCalFim.getTime()); 
        
        return consolidadorDeVendasDAO.getListarConsolidadorDeVendasStart(dataVendaInicial,dataVendaFinal);
    }
    
    
    public String obterFilialDaConsulta(){
        
        String lojasDaConsulta = "";
    
        for (ConsolidadorDeVendas p : myQuery) {
            lojasDaConsulta = p.getFilialApelido();
        }
            
        return lojasDaConsulta;
    }
    
    public String obterMesAnoDaConsulta(){
        return null;
    }
    
    
    public String obterTituloParametro(){
        int mesAtual =0;
        String mesDaConsulta = "";
        Date data = new Date();
        GregorianCalendar dataAtual = new GregorianCalendar();
        dataAtual.setTime(data);
        String lojasDaConsulta = "";
        
        String tituloDaConsulta = "";
        
        
        if(consolidadorDeVendas.getMesVenda()==null && consolidadorDeVendas.getFilial()==null){
            Calendar cal = Calendar.getInstance();
            int month = cal.get(Calendar.MONTH) + 1;
            mesDaConsulta = obterMesPorExtenso(month);
                        
            tituloDaConsulta = "Resultados todas as lojas /"+mesDaConsulta;
            
        }else{
             mesAtual = Integer.parseInt(consolidadorDeVendas.getMesVenda());
             mesDaConsulta = obterMesPorExtenso(mesAtual);
            tituloDaConsulta = mesDaConsulta;
        }
        
        return tituloDaConsulta;
    }
    
    
    
    public String obterPeriodoDatasFormatadas(String tipo){
        
        if(tipo.equals("1")){
            Date data1 = new Date();
            GregorianCalendar dataCalIni1 = new GregorianCalendar();
            GregorianCalendar dataCalFim1 = new GregorianCalendar();
            dataCalIni1.setTime(data1);
            dataCalFim1.setTime(data1);

            int diaIni1 = dataCalIni1.get(Calendar.DAY_OF_MONTH);
            int diaFim1 = dataCalIni1.get(Calendar.DAY_OF_MONTH)-1;

            dataCalIni1.set(Calendar.DAY_OF_MONTH, 1);
            dataCalFim1.set(Calendar.DAY_OF_MONTH, diaFim1);

            SimpleDateFormat out = new SimpleDateFormat("dd/MM/yyyy");
            String dataVendaInicial1 = out.format(dataCalIni1.getTime()); 
            String dataVendaFinal1   = out.format(dataCalFim1.getTime()); 
            
            
            
            dataFormatadaPeriodo = dataVendaInicial1 + " até "+dataVendaFinal1;
        }
        
        if(tipo.equals("2")){
            SimpleDateFormat dataFormatadaMensagem = new SimpleDateFormat("dd/MM/YYYY");
            String dataVendaInicial = dataFormatadaMensagem.format(consolidadorDeVendas.getDataVendaIni()); 
            String dataVendaFinal = dataFormatadaMensagem.format(consolidadorDeVendas.getDataVendaFim());
            
            dataFormatadaPeriodo = dataVendaInicial + " até "+dataVendaFinal;
        }
        
        return dataFormatadaPeriodo;
    }
    
    public void clearAll(){
        myQuery = obterListaConsolidadorDeVendasInicial();
        
        
        //Percentual de vendas filial
        criarGraficos();
        
        //Movimentações Diarias
        listaMovimentacoesDiarias = obterListaMovimentacoesDiariasInicial();
        
        //Movimentações de Grupo de Produtos
        listaMovDeGrupoDeProdutos = obterListaMovimentacoesDeGrupoDeProdutosInicial();
        
        //Criar os Totais de DataTable de Grupos por Produtos
        gerarTotaisPorTipo("1");
        
        
        //Obter Data Formatada
        //dataFormatadaPeriodo = obterPeriodoDatasFormatadas("1");
        
        consolidadorDeVendas.setFilial(null);
        consolidadorDeVendas.setDataVendaIni(null);
        consolidadorDeVendas.setDataVendaFim(null);
        consolidadorDeVendas.setMesVenda(null);
        
        
    }
    
    
    public boolean validaObjetos(){
        
        if(consolidadorDeVendas.getMesVenda()==null && consolidadorDeVendas.getFilial()==null){
           // sendErrorMessageToUser("Filial e Mês de vendas não informados, preenchimento obrigatório.");
            return false;
        }
        
        return true;
    }
    
   public void gerarPeriodoMensal(){
        String mes = consolidadorDeVendas.getMesVenda();
               
        Date dataAtual = new Date();
        GregorianCalendar dataPeriodoIni = new GregorianCalendar();
        GregorianCalendar dataPeriodoFim = new GregorianCalendar();
        dataPeriodoIni.setTime(dataAtual);
        dataPeriodoFim.setTime(dataAtual);
        
        int diaDataPeriodoIni = dataPeriodoIni.get(Calendar.DAY_OF_MONTH);
        int mesAtual = Integer.parseInt(consolidadorDeVendas.getMesVenda())-1;
        int anoAtual = dataPeriodoIni.get(Calendar.YEAR);
        
        //Obtendo Periodo Inicial Mensal              
        dataPeriodoIni.set(Calendar.DAY_OF_MONTH, 1);
        dataPeriodoIni.set(Calendar.MONTH, mesAtual);
        dataPeriodoIni.set(Calendar.YEAR, anoAtual);
        
        //Obtendo Peiodo Final Mensal 
        dataPeriodoFim.set(Calendar.MONTH, mesAtual);  
        dataPeriodoFim.set(Calendar.YEAR, anoAtual);    
        
        SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
        String dataMensalInicial = out.format(dataPeriodoIni.getTime());
       
        int ultimodia = dataPeriodoFim.getActualMaximum(Calendar.DAY_OF_MONTH);
        dataPeriodoFim.set(Calendar.DAY_OF_MONTH, ultimodia);  
        String dataMensalFinal = out.format(dataPeriodoFim.getTime());
        
        // Setando Objetos
        consolidadorDeVendas.setDataVendaIni(dataPeriodoIni.getTime());
        consolidadorDeVendas.setDataVendaFim(dataPeriodoFim.getTime());
       
   }
    public void buscarVendasPorFiliaisPeriodo() throws ParseException{
        
        if(validaObjetos()){
            String listaDeFiliais = consolidadorDeVendas.getFilial();
            String myFilial = "";
            
            //função que gerar o periodo mensal informado na tela
            gerarPeriodoMensal();
            
            String dataVendaIniSelecionada = getDateToSQL();
            String dataVendaFimSelecionada = getDateToSQL();
            
            
            if(consolidadorDeVendas.getDataVendaIni()!=null){
                SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
                String dataVendaInicial = out.format(consolidadorDeVendas.getDataVendaIni()); 
                dataVendaIniSelecionada = dataVendaInicial;
            } 

            if(consolidadorDeVendas.getDataVendaFim()!=null){
                SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
                String dataVendaFinal = out.format(consolidadorDeVendas.getDataVendaFim()); 
                dataVendaFimSelecionada = dataVendaFinal;
            }
            
        
            
            if ((consolidadorDeVendas.getDataVendaIni()!=null) && consolidadorDeVendas.getDataVendaFim()!=null){
                if (validaPeriodoDeVenda()){

                  if(consolidadorDeVendasDAO.getListarConsolidadorDeVendas(listaDeFiliais,dataVendaIniSelecionada,dataVendaFimSelecionada).isEmpty()){
                      sendErrorMessageToUser("A consulta não obteve resultados");
                      clearAll();
                  }else{
                    myQuery = consolidadorDeVendasDAO.getListarConsolidadorDeVendas(listaDeFiliais,dataVendaIniSelecionada,dataVendaFimSelecionada);

                    graficoPercentualDeVendas = new PieChartModel();
                    listaGraficoPercentualVendas = consolidadorDeVendasDAO.getGraficoDePercentualdeVendas(listaDeFiliais,dataVendaIniSelecionada,dataVendaFimSelecionada);

                    if (listaGraficoPercentualVendas.size()==1){
                        for(ConsolidadorDeVendas ConsolidadorDeVendas : listaGraficoPercentualVendas){
                            graficoPercentualDeVendas.set(ConsolidadorDeVendas.getSiglaFilial()+" ", ConsolidadorDeVendas.getPercentual_vendas());
                        }
                        graficoPercentualDeVendas.set("Rede", 100.00);
                    }else{
                        for(ConsolidadorDeVendas ConsolidadorDeVendas : listaGraficoPercentualVendas){
                            graficoPercentualDeVendas.set(ConsolidadorDeVendas.getSiglaFilial(), ConsolidadorDeVendas.getPercentual_vendas());
                        } 
                    }
                    SimpleDateFormat dataFormatadaMensagem = new SimpleDateFormat("dd/MM/YYYY");
                    String dataVendaInicial = dataFormatadaMensagem.format(consolidadorDeVendas.getDataVendaIni()); 
                    String dataVendaFinal = dataFormatadaMensagem.format(consolidadorDeVendas.getDataVendaFim());

                    //graficoPercentualDeVendas.setTitle("Vendas no período de "+dataVendaInicial+" até "+dataVendaFinal);
                    graficoPercentualDeVendas.setTitle("% Venda");
                    graficoPercentualDeVendas.setShowDataLabels(true);
                    graficoPercentualDeVendas.setDiameter(150);
                    graficoPercentualDeVendas.setFill(true);
                    graficoPercentualDeVendas.setLegendCols(1);
                    graficoPercentualDeVendas.setLegendPosition("w"); 

                    // Obtem lista de Vendas diarias
                    listaMovimentacoesDiarias = consolidadorDeVendasDAO.getListarMovimentacoesDiarias(listaDeFiliais, 
                                                                                                      dataVendaIniSelecionada, 
                                                                                                      dataVendaFimSelecionada);
                    
                    //Tamanho da lista para setar o uso dos subtotais
                    totaisMovDiariasCount = listaMovimentacoesDiarias.size()-1;
                    
                    // Grafico Percentual de LinhaProduto
                    graficoPercentualDeLinhaProduto = new PieChartModel();
                    listaGraficoPercentualDeLinhaProduto = consolidadorDeVendasDAO.getGraficoPercentualPorLinhaProduto(listaDeFiliais,dataVendaIniSelecionada,dataVendaFimSelecionada);
                    for(ConsolidadorDeVendas ConsolidadorDeVendas : listaGraficoPercentualDeLinhaProduto){
                        graficoPercentualDeLinhaProduto.set(ConsolidadorDeVendas.getLinha(), ConsolidadorDeVendas.getPercentual());
                    } 

                    //graficoPercentualDeLinhaProduto.setTitle("% Linha de Produtos vendidos entre "+dataVendaInicial+" até "+dataVendaFinal);
                    graficoPercentualDeLinhaProduto.setTitle("% Venda");
                    graficoPercentualDeLinhaProduto.setShowDataLabels(true);
                    graficoPercentualDeLinhaProduto.setDiameter(150);
                    graficoPercentualDeLinhaProduto.setFill(true);
                    graficoPercentualDeLinhaProduto.setLegendCols(1);
                    //graficoPercentualDeLinhaProduto.setLegendRows(2);
                    graficoPercentualDeLinhaProduto.setLegendPosition("w"); 


                   //Lista de Movimentações de vendedores por grupos de produtos
                    listaMovDeGrupoDeProdutos = consolidadorDeVendasDAO.getTotaisPorGrupoProduto(listaDeFiliais, dataVendaIniSelecionada, dataVendaFimSelecionada,"");
                    
                    //Obter Data Formatada
                    //dataFormatadaPeriodo = obterPeriodoDatasFormatadas("2");
                    //sendInfoMessageToUser("Período de consulta de "+dataVendaInicial+" até "+dataVendaFinal); 
                    
                    //obter titulo apos a consulta
                    //titulo = obterTituloParametro();
                    
                    //Obter Filial Atual Padrao usado no titulo
                    filialAtualVendas = obterFilialDaConsulta();
                    
                    
                    //Obter Mes Atual Por extenso usado no titulo
                    mesAtualVendasPorExtenso = obterTituloParametro();
                    
                    
                    //obter vendedoras e categorias
                     listaDeVendedores = obterListadeVendedoresCategorias();
                     
                    //obter vendas por categorias 
                    listaDeVendasPorCategorias = consolidadorDeVendasDAO.getVendasPorCategorias(listaDeFiliais, dataVendaIniSelecionada, dataVendaFimSelecionada);
                     
                   // obterListadeVendedoresCategorias(consolidadorDeVendas.getFilial(), consolidadorDeVendas.getVendedor());
                    
                    //Grafico de Grade media
                    graficoGradeMedia = new BarChartModel();
                    ChartSeries calcados = new ChartSeries();
                    calcados.setLabel("Calçados");
                    int maxQtdeVendida = 0;
                    listaGraficoGradeMedia = consolidadorDeVendasDAO.getGraficoGradeMedia(listaDeFiliais,dataVendaInicial,dataVendaFinal);
                    for(ConsolidadorDeVendas ConsolidadorDeVendas : listaGraficoGradeMedia){
                        maxQtdeVendida = ConsolidadorDeVendas.getQtde_vendida();
                        calcados.set(ConsolidadorDeVendas.getTamanho(), ConsolidadorDeVendas.getQtdeLiquida());
                    } 
                    
                    maxQtdeVendida +=10;
                    
                    graficoGradeMedia.addSeries(calcados);
                    graficoGradeMedia.setTitle("Grade Média");
                    graficoGradeMedia.setAnimate(true);
                    //graficoGradeMedia.setLegendPosition("ne");
                    Axis yAxis = graficoGradeMedia.getAxis(AxisType.Y);
                    yAxis.setTickFormat("%d");
                    yAxis.setMin(0);
                    yAxis.setMax(maxQtdeVendida);
                     
                     consolidadorDeVendas.setFilial(null);
                     consolidadorDeVendas.setMesVenda(null);
                    
                  }
                }//Valida Periodo de Venda
            }// Valida preenchimento das datas
        
        }// fim da valida de objetos preenchidos
        
    }// fim da classe
    
    
    public void buscarVendasTestes() throws ParseException{
        if(validaObjetos()){
            
            String listaDeFiliais = consolidadorDeVendas.getFilial();
            String myFilial = "";
            
            //função que gerar o periodo mensal informado na tela
            gerarPeriodoMensal();
            
            String dataVendaIniSelecionada = getDateToSQL();
            String dataVendaFimSelecionada = getDateToSQL();
            
            
            if(consolidadorDeVendas.getDataVendaIni()!=null){
                SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
                String dataVendaInicial = out.format(consolidadorDeVendas.getDataVendaIni()); 
                dataVendaIniSelecionada = dataVendaInicial;
            } 

            if(consolidadorDeVendas.getDataVendaFim()!=null){
                SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
                String dataVendaFinal = out.format(consolidadorDeVendas.getDataVendaFim()); 
                dataVendaFimSelecionada = dataVendaFinal;
            }
            
            
            if ((consolidadorDeVendas.getDataVendaIni()!=null) && 
                 consolidadorDeVendas.getDataVendaFim()!=null && 
                 consolidadorDeVendas.getFilial()!=null){
      
                //Lista Vendedoras
                myQuery = consolidadorDeVendasDAO.getListarConsolidadorDeVendas(listaDeFiliais,dataVendaIniSelecionada,dataVendaFimSelecionada);
                
                
                graficoPercentualDeVendas = new PieChartModel();
                listaGraficoPercentualVendas = consolidadorDeVendasDAO.getGraficoDePercentualdeVendas(listaDeFiliais,dataVendaIniSelecionada,dataVendaFimSelecionada);

                if (listaGraficoPercentualVendas.size()==1){
                    for(ConsolidadorDeVendas ConsolidadorDeVendas : listaGraficoPercentualVendas){
                        graficoPercentualDeVendas.set(ConsolidadorDeVendas.getSiglaFilial()+" ", ConsolidadorDeVendas.getPercentual_vendas());
                    }
                    graficoPercentualDeVendas.set("Rede", 100.00);
                }else{
                    for(ConsolidadorDeVendas ConsolidadorDeVendas : listaGraficoPercentualVendas){
                        graficoPercentualDeVendas.set(ConsolidadorDeVendas.getSiglaFilial(), ConsolidadorDeVendas.getPercentual_vendas());
                    } 
                }
                
                SimpleDateFormat dataFormatadaMensagem = new SimpleDateFormat("dd/MM/YYYY");
                String dataVendaInicial = dataFormatadaMensagem.format(consolidadorDeVendas.getDataVendaIni()); 
                String dataVendaFinal = dataFormatadaMensagem.format(consolidadorDeVendas.getDataVendaFim());

                //graficoPercentualDeVendas.setTitle("Vendas no período de "+dataVendaInicial+" até "+dataVendaFinal);
                graficoPercentualDeVendas.setTitle("% Venda");
                graficoPercentualDeVendas.setShowDataLabels(true);
                graficoPercentualDeVendas.setDiameter(150);
                graficoPercentualDeVendas.setFill(true);
                graficoPercentualDeVendas.setLegendCols(1);
                graficoPercentualDeVendas.setLegendPosition("w"); 

                    
                // Grafico Percentual de LinhaProduto
                graficoPercentualDeLinhaProduto = new PieChartModel();
                listaGraficoPercentualDeLinhaProduto = consolidadorDeVendasDAO.getGraficoPercentualPorLinhaProduto(listaDeFiliais,dataVendaIniSelecionada,dataVendaFimSelecionada);
                for(ConsolidadorDeVendas ConsolidadorDeVendas : listaGraficoPercentualDeLinhaProduto){
                    graficoPercentualDeLinhaProduto.set(ConsolidadorDeVendas.getLinha(), ConsolidadorDeVendas.getPercentual());
                } 

                //graficoPercentualDeLinhaProduto.setTitle("% Linha de Produtos vendidos entre "+dataVendaInicial+" até "+dataVendaFinal);
                graficoPercentualDeLinhaProduto.setTitle("% Venda");
                graficoPercentualDeLinhaProduto.setShowDataLabels(true);
                graficoPercentualDeLinhaProduto.setDiameter(150);
                graficoPercentualDeLinhaProduto.setFill(true);
                graficoPercentualDeLinhaProduto.setLegendCols(1);
                graficoPercentualDeLinhaProduto.setLegendPosition("w"); 

                
                
                // Obtem lista de Vendas diarias
                listaMovimentacoesDiarias = consolidadorDeVendasDAO.getListarMovimentacoesDiarias(listaDeFiliais, 
                                                                                                      dataVendaIniSelecionada, 
                                                                                                      dataVendaFimSelecionada);
                 
                
                //obter vendedoras e categorias
                listaDeVendedores = obterListadeVendedoresCategorias();
                
                
                //obter vendas por categorias 
                listaDeVendasPorCategorias = consolidadorDeVendasDAO.getVendasPorCategorias(listaDeFiliais, dataVendaIniSelecionada, dataVendaFimSelecionada);
                
                
                
                //Grafico de Grade media
                graficoGradeMedia = new BarChartModel();
                ChartSeries calcados = new ChartSeries();
                calcados.setLabel("Calçados");
                int maxQtdeVendida = 0;
                listaGraficoGradeMedia = consolidadorDeVendasDAO.getGraficoGradeMedia(listaDeFiliais,dataVendaInicial,dataVendaFinal);
                for(ConsolidadorDeVendas ConsolidadorDeVendas : listaGraficoGradeMedia){
                    maxQtdeVendida = ConsolidadorDeVendas.getQtde_vendida();
                    calcados.set(ConsolidadorDeVendas.getTamanho(), ConsolidadorDeVendas.getQtdeLiquida());
                } 

                maxQtdeVendida +=10;

                graficoGradeMedia.addSeries(calcados);
                graficoGradeMedia.setTitle("Grade Média");
                graficoGradeMedia.setAnimate(true);
                //graficoGradeMedia.setLegendPosition("ne");
                Axis yAxis = graficoGradeMedia.getAxis(AxisType.Y);
                yAxis.setTickFormat("%d");
                yAxis.setMin(0);
                yAxis.setMax(maxQtdeVendida);
                     
                //Obter Filial Atual Padrao usado no titulo
                filialAtualVendas = obterFilialDaConsulta();
                    
                //Obter Mes Atual Por extenso usado no titulo
                mesAtualVendasPorExtenso = obterTituloParametro();
                   
      
               
                   
            
            }
            
            
            
        }
        
        consolidadorDeVendas.setFilial(null);
        consolidadorDeVendas.setDataVendaIni(null);
        consolidadorDeVendas.setDataVendaFim(null);
        consolidadorDeVendas.setMesVenda(null);
        
                    
    }
    
    public BigDecimal obterTotalMovimentacaoDiariaPorLoja(String listaDeFiliais, Date dataVenda) throws ParseException{
        BigDecimal total = new BigDecimal(0);
        
        
        Date dataAtual = dataVenda;
        GregorianCalendar dataPeriodoIni = new GregorianCalendar();
        GregorianCalendar dataPeriodoFim = new GregorianCalendar();
        dataPeriodoIni.setTime(dataAtual);
        dataPeriodoFim.setTime(dataAtual);
       
        int diaIni = dataPeriodoIni.get(Calendar.DAY_OF_MONTH);
        int diaFim = dataPeriodoFim.get(Calendar.DAY_OF_MONTH);
        
        dataPeriodoIni.set(Calendar.DAY_OF_MONTH, 1);
        dataPeriodoFim.set(Calendar.DAY_OF_MONTH, diaFim);
        
        SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
        String dataMensalInicial = out.format(dataPeriodoIni.getTime());
        String dataMensalFinal = out.format(dataPeriodoFim.getTime());
        
        
        //Obtendo Mes da Consulta Inicial e Final
        int mesDaConsultaIni =  dataPeriodoIni.get(Calendar.MONTH)+1;
        int mesDaConsultaFim = dataPeriodoFim.get(Calendar.MONTH)+1;
          
        
        if(mesDaConsultaIni==mesDaConsultaFim){
            
            List<ConsolidadorDeVendas> listaDeVendasAcumuladas = consolidadorDeVendasDAO.getListarTotaisMovimentacoesDiariasPorFilial("'"+listaDeFiliais+"'", dataMensalInicial, dataMensalFinal);
            for (ConsolidadorDeVendas p : listaDeVendasAcumuladas) {
                
                total = total.add(p.getValorLiquidoVenda());
            }
        }
        
        return total;
       
    
    }
    
    public boolean coordenaMesExibicao(String mesAtual){
        
        
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        int mesValida = Integer.parseInt(mesAtual);
        
        if(month<mesValida){
            return true;
        }
        
        
        return false;
    }
    
    private List<ConsolidadorDeVendas> obterListaMovimentacoesDiariasInicial(){
        Date data = new Date();
        GregorianCalendar dataCalIni = new GregorianCalendar();
        GregorianCalendar dataCalFim = new GregorianCalendar();
        
        dataCalIni.setTime(data);
        dataCalFim.setTime(data);
        
        int diaIni = dataCalIni.get(Calendar.DAY_OF_MONTH);
        int diaFim = dataCalIni.get(Calendar.DAY_OF_MONTH)-1;
        
        dataCalIni.set(Calendar.DAY_OF_MONTH, 1);
        dataCalFim.set(Calendar.DAY_OF_MONTH, diaFim);
       
        SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
        String dataVendaInicial = out.format(dataCalIni.getTime()); 
        String dataVendaFinal   = out.format(dataCalFim.getTime()); 
        
        return consolidadorDeVendasDAO.getListarMovimentacoesDiariasStart(dataVendaInicial, dataVendaFinal);
        
    }
    
    
    
     private List<ConsolidadorDeVendas> obterListaMovimentacoesDeGrupoDeProdutosInicial(){
        Date data = new Date();
        GregorianCalendar dataCalIni = new GregorianCalendar();
        GregorianCalendar dataCalFim = new GregorianCalendar();
        
        dataCalIni.setTime(data);
        dataCalFim.setTime(data);
        
        int diaIni = dataCalIni.get(Calendar.DAY_OF_MONTH);
        int diaFim = dataCalIni.get(Calendar.DAY_OF_MONTH)-1;
        
        dataCalIni.set(Calendar.DAY_OF_MONTH, 1);
        dataCalFim.set(Calendar.DAY_OF_MONTH, diaFim);
       
        SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
        String dataVendaInicial = out.format(dataCalIni.getTime()); 
        String dataVendaFinal   = out.format(dataCalFim.getTime()); 
        
        return consolidadorDeVendasDAO.getListarMovimentacoesPorGrupoProdutoStart(dataVendaInicial, dataVendaFinal);
        
    }
    
     
      public List<ConsolidadorDeVendas> obterListaVendasPorCategoriasInicial(){
        Date data = new Date();
        GregorianCalendar dataCalIni = new GregorianCalendar();
        GregorianCalendar dataCalFim = new GregorianCalendar();
        
        dataCalIni.setTime(data);
        dataCalFim.setTime(data);
        
        int diaIni = dataCalIni.get(Calendar.DAY_OF_MONTH);
        int diaFim = dataCalIni.get(Calendar.DAY_OF_MONTH)-1;
        
        dataCalIni.set(Calendar.DAY_OF_MONTH, 1);
        dataCalFim.set(Calendar.DAY_OF_MONTH, diaFim);
       
        SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
        String dataVendaInicial = out.format(dataCalIni.getTime()); 
        String dataVendaFinal   = out.format(dataCalFim.getTime()); 
        
               
        return consolidadorDeVendasDAO.getVendasPorCategoriasStart(dataVendaInicial, dataVendaFinal);
        
    }
     
     private List<ConsolidadorDeVendas> obterListadeVendedoresCategoriasInicial(){
         Date data = new Date();
        GregorianCalendar dataCalIni = new GregorianCalendar();
        GregorianCalendar dataCalFim = new GregorianCalendar();
        
        dataCalIni.setTime(data);
        dataCalFim.setTime(data);
        
        int diaIni = dataCalIni.get(Calendar.DAY_OF_MONTH);
        int diaFim = dataCalIni.get(Calendar.DAY_OF_MONTH)-1;
        
        dataCalIni.set(Calendar.DAY_OF_MONTH, 1);
        dataCalFim.set(Calendar.DAY_OF_MONTH, diaFim);
       
        SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
        String dataVendaInicial = out.format(dataCalIni.getTime()); 
        String dataVendaFinal   = out.format(dataCalFim.getTime()); 
        
        return  consolidadorDeVendasDAO.getListarVendedoresStart(dataVendaInicial, dataVendaFinal);
     
     }
     
     
    private List<ConsolidadorDeVendas> obterListadeVendedoresCategorias(){
        
        SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
        String dataMensalInicial = out.format(consolidadorDeVendas.getDataVendaIni());
        String dataMensalFinal = out.format(consolidadorDeVendas.getDataVendaFim());
            
        String dataVendaIniSelecionada = dataMensalInicial;
        String dataVendaFimSelecionada = dataMensalFinal;
        
        String listaDeFiliais = consolidadorDeVendas.getFilial();
        
        listaDeVendedores = consolidadorDeVendasDAO.getListarVendedores(listaDeFiliais, 
                                                                        dataVendaIniSelecionada, 
                                                                        dataVendaFimSelecionada);
        
        return  listaDeVendedores;
     
     }
     
     public List<ConsolidadorDeVendas> obterListadeVendedoresCategorias(String filial, String vendedor){
        
        String dataVendaIniSelecionada = "";
        String dataVendaFimSelecionada = "";
        String dataMensalInicial = "";
        String dataMensalFinal = "";
                
        if(consolidadorDeVendas.getDataVendaIni()==null && consolidadorDeVendas.getDataVendaFim()==null){ 
            
            Date dataAtual = new Date();
            GregorianCalendar dataPeriodoIni = new GregorianCalendar();
            GregorianCalendar dataPeriodoFim = new GregorianCalendar();
            dataPeriodoIni.setTime(dataAtual);
            dataPeriodoFim.setTime(dataAtual);

            int diaDataPeriodoIni = dataPeriodoIni.get(Calendar.DAY_OF_MONTH);
            int mesAtual = dataPeriodoIni.get(Calendar.MONTH);
            int anoAtual = dataPeriodoIni.get(Calendar.YEAR);

            //Obtendo Periodo Inicial Mensal              
            dataPeriodoIni.set(Calendar.DAY_OF_MONTH, 1);
            dataPeriodoIni.set(Calendar.MONTH, mesAtual);
            dataPeriodoIni.set(Calendar.YEAR, anoAtual);

            //Obtendo Peiodo Final Mensal 
            dataPeriodoFim.set(Calendar.MONTH, mesAtual);  
            dataPeriodoFim.set(Calendar.YEAR, anoAtual);    

            SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
            dataMensalInicial = out.format(dataPeriodoIni.getTime());

            int ultimodia = dataPeriodoFim.getActualMaximum(Calendar.DAY_OF_MONTH);
            dataPeriodoFim.set(Calendar.DAY_OF_MONTH, ultimodia);  
            dataMensalFinal = out.format(dataPeriodoFim.getTime());

            // Setando Objetos
            consolidadorDeVendas.setDataVendaIni(dataPeriodoIni.getTime());
            consolidadorDeVendas.setDataVendaFim(dataPeriodoFim.getTime());

            String dataVendaInicial = out.format(consolidadorDeVendas.getDataVendaIni()); 
            dataVendaIniSelecionada = dataVendaInicial;

            String dataVendaFinal = out.format(consolidadorDeVendas.getDataVendaFim()); 
            dataVendaFimSelecionada = dataVendaFinal;
        
        }else{
            
            SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
            dataMensalInicial = out.format(consolidadorDeVendas.getDataVendaIni());
            dataMensalFinal = out.format(consolidadorDeVendas.getDataVendaFim());
            
            dataVendaIniSelecionada = dataMensalInicial;
            dataVendaFimSelecionada = dataMensalFinal;
            
        }   
        
        String listaDeFiliais = filial;
        
        
//         System.out.println("[listaDeFiliais] "+listaDeFiliais);
//        System.out.println("[vendedor]"+vendedor);
//        System.out.println("[dataMensalInicial]"+dataMensalInicial);
//        System.out.println("[dataMensalFinal]"+dataMensalFinal);
         
        
        listaDeTotaisDeVendedoresCategorias = consolidadorDeVendasDAO.getTotaisPorVendedoresCategorias(
                    "'"+listaDeFiliais+"'", 
                    dataVendaIniSelecionada, 
                    dataVendaFimSelecionada,
                    vendedor); 
        
        
         
        return listaDeTotaisDeVendedoresCategorias;

     
     }
     
     
     public void gerarTotaisPorTipo(String tipoConsulta){
        if("1".equals(tipoConsulta)){
            //Consulta Inicial para grupo de produtos
            Date data = new Date();
            GregorianCalendar dataCalIni = new GregorianCalendar();
            GregorianCalendar dataCalFim = new GregorianCalendar();

            dataCalIni.setTime(data);
            dataCalFim.setTime(data);

            int diaIni = dataCalIni.get(Calendar.DAY_OF_MONTH);
            int diaFim = dataCalIni.get(Calendar.DAY_OF_MONTH)-1;

            dataCalIni.set(Calendar.DAY_OF_MONTH, 1);
            dataCalFim.set(Calendar.DAY_OF_MONTH, diaFim);

            SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
            String dataVendaInicial = out.format(dataCalIni.getTime()); 
            String dataVendaFinal   = out.format(dataCalFim.getTime());
            
            listaTotaisDeGrupoDeProdutos = consolidadorDeVendasDAO.getTotaisPorGrupoProdutoStart(dataVendaInicial, dataVendaFinal);
            
        }
        
     }
     
     public List<ConsolidadorDeVendas> obterTotaisSubGrupoProduto(String filial, 
                                                                  String vendedorApelido){
        String listaDeFiliais = "'"+filial+"'";

         //Consulta Inicial para grupo de produtos
        Date data = new Date();
        GregorianCalendar dataCalIni = new GregorianCalendar();
        GregorianCalendar dataCalFim = new GregorianCalendar();

        dataCalIni.setTime(data);
        dataCalFim.setTime(data);

        int diaIni = dataCalIni.get(Calendar.DAY_OF_MONTH);
        int diaFim = dataCalIni.get(Calendar.DAY_OF_MONTH)-1;

        dataCalIni.set(Calendar.DAY_OF_MONTH, 1);
        dataCalFim.set(Calendar.DAY_OF_MONTH, diaFim);

        SimpleDateFormat out = new SimpleDateFormat("yyyyMMdd");
        String dataVendaInicial = out.format(dataCalIni.getTime()); 
        String dataVendaFinal   = out.format(dataCalFim.getTime());
        
        String vendedor = "'"+vendedorApelido+"'";
        
        return listaTotaisDeGrupoDeProdutos = consolidadorDeVendasDAO.getTotaisPorGrupoProduto(listaDeFiliais, dataVendaInicial, dataVendaFinal, vendedor);
     }
     
     public int getTotalQtdeLiquida(FilterEvent filterEvent,String vendedorApelido) {
        int total = 0;

         for(int i = 0; i < listaTotaisDeGrupoDeProdutos.size(); i ++){ 
             if(listaTotaisDeGrupoDeProdutos.get(i).getVendedorApelido().equals(vendedorApelido)){   
                 total += listaTotaisDeGrupoDeProdutos.get(i).getQtdeLiquida();
            }
         }
        
        return total;
    }
    
     
      public int getTotalQtdeVendida(FilterEvent filterEvent,String vendedorApelido) {
        int total = 0;
         for(int i = 0; i < listaTotaisDeGrupoDeProdutos.size(); i ++){ 
             if(listaTotaisDeGrupoDeProdutos.get(i).getVendedorApelido().equals(vendedorApelido)){   
                 total += listaTotaisDeGrupoDeProdutos.get(i).getQtde_venda();
            }
         }
        
        return total;
        
    }
    
    public int getTotalQtdeTroca(FilterEvent filterEvent, String vendedorApelido) {
        int total = 0;
         for(int i = 0; i < listaTotaisDeGrupoDeProdutos.size(); i ++){ 
             if(listaTotaisDeGrupoDeProdutos.get(i).getVendedorApelido().equals(vendedorApelido)){   
                 total += listaTotaisDeGrupoDeProdutos.get(i).getQtdeTroca();
            }
         }
        
        return total;
        
    }
    
    public BigDecimal getTotalValorLiquido(FilterEvent filterEvent, String vendedor) {
     
        BigDecimal total = new BigDecimal(0);
        
        for (ConsolidadorDeVendas listaTotaisDeGrupoDeProduto : listaTotaisDeGrupoDeProdutos) {
            if (listaTotaisDeGrupoDeProduto.getVendedor().equals(vendedor)) {
                total = total.add(listaTotaisDeGrupoDeProduto.getValorLiquidoVenda());
            } 
        }

        return total;
    }
    
    public float getTotalValorVendas(FilterEvent filterEvent, String vendedor) {
        
        float total = 0;
        
        for (ConsolidadorDeVendas listaTotaisDeGrupoDeProduto : listaTotaisDeGrupoDeProdutos) {
            if (listaTotaisDeGrupoDeProduto.getVendedor().equals(vendedor)) {
                
                total += listaTotaisDeGrupoDeProduto.getValor_venda();
            } 
        }

        return total;
        
    } 
     
    public BigDecimal getTotalValorTrocas(FilterEvent filterEvent, String vendedor) {
       BigDecimal total = new BigDecimal(0);
        
        for (ConsolidadorDeVendas listaTotaisDeGrupoDeProduto : listaTotaisDeGrupoDeProdutos) {
            if (listaTotaisDeGrupoDeProduto.getVendedor().equals(vendedor)) {
                total = total.add(listaTotaisDeGrupoDeProduto.getValorTroca());
            } 
        }

        return total;
        
    }
    
    // total geral
    
    
    public String getTotalGeralValor(String tipoLista, String tipoColuna){
        DecimalFormat decFormat = new java.text.DecimalFormat("#,###,##0.00");
       
        if(tipoLista.equals("1") && tipoColuna.equals("valorLiquidoVenda")){
           BigDecimal total = new BigDecimal(0); 
           for (ConsolidadorDeVendas p : getMyQuery()) {
                total = total.add(p.getValorLiquidoVenda());
            }
        
           return decFormat.format(total); 
        }
        
        if(tipoLista.equals("1") && tipoColuna.equals("valorMeta")){
           BigDecimal total = new BigDecimal(0); 
           for (ConsolidadorDeVendas p : getMyQuery()) {
                total = total.add(p.getValorMeta());
            }
        
           return decFormat.format(total); 
        }
        
        if(tipoLista.equals("1") && tipoColuna.equals("pecasAtendimento")){
           BigDecimal total = new BigDecimal(0); 
            
           for (ConsolidadorDeVendas p : getMyQuery()) {
                total = total.add(p.getPecasAtendimento());
            }
        
           return decFormat.format(total);
        }
        
         if(tipoLista.equals("1") && tipoColuna.equals("qtdeTroca")){
           int total = 0; 
           for (ConsolidadorDeVendas p : getMyQuery()) {
                total+= p.getQtdeTroca();
            }
        
           return Integer.toString(total);
        }
        
        if(tipoLista.equals("1") && tipoColuna.equals("valorTroca")){
           BigDecimal total = new BigDecimal(0); 
           for (ConsolidadorDeVendas p : getMyQuery()) {
                total = total.add(p.getValorTroca());
            }
        
           return decFormat.format(total); 
        } 
         
        if(tipoLista.equals("1") && tipoColuna.equals("vendaMedia")){
           BigDecimal total = new BigDecimal(0); 
           for (ConsolidadorDeVendas p : getMyQuery()) {
                total = total.add(p.getVendaMedia());
            }
        
           return decFormat.format(total); 
        } 
         
        if(tipoLista.equals("1") && tipoColuna.equals("bolsasAtendimento")){
           BigDecimal total = new BigDecimal(0); 
           for (ConsolidadorDeVendas p : getMyQuery()) {
                total = total.add(p.getBolsasAtendimento());
            }
        
            return decFormat.format(total); 
        }
        
        if(tipoLista.equals("1") && tipoColuna.equals("calcadosAtendimento")){
           BigDecimal total = new BigDecimal(0); 
           for (ConsolidadorDeVendas p : getMyQuery()) {
                total = total.add(p.getCalcadosAtendimento());
            }
        
            return decFormat.format(total); 
        }
        
         if(tipoLista.equals("1") && tipoColuna.equals("outrosAtendimento")){
           BigDecimal total = new BigDecimal(0); 
           for (ConsolidadorDeVendas p : getMyQuery()) {
               total = total.add(p.getOutrosAtendimento());
            }
        
          return decFormat.format(total); 
        }
        
        if(tipoLista.equals("2") && tipoColuna.equals("qtdeLiquida")){
           int total = 0; 
           for (ConsolidadorDeVendas p : getListaMovimentacoesDiarias()) {
                total+= p.getQtdeLiquida();
            }
        
           return Integer.toString(total);
        }
        
        
        if(tipoLista.equals("2") && tipoColuna.equals("qtdeVendida")){
           int total = 0; 
           for (ConsolidadorDeVendas p : getListaMovimentacoesDiarias()) {
                total+= p.getQtde_vendida();
            }
        
           return Integer.toString(total);
        }
        
         if(tipoLista.equals("2") && tipoColuna.equals("qtdeTroca")){
           int total = 0; 
           for (ConsolidadorDeVendas p : getListaMovimentacoesDiarias()) {
                total+= p.getQtdeTroca();
            }
        
           return Integer.toString(total);
        }
         
        if(tipoLista.equals("2") && tipoColuna.equals("valorLiquidoVenda")){
           BigDecimal total = new BigDecimal(0); 
           for (ConsolidadorDeVendas p : getListaMovimentacoesDiarias()) {
                total = total.add(p.getValorLiquidoVenda());
            }
        
           return decFormat.format(total); 
        }  
        
          if(tipoLista.equals("2") && tipoColuna.equals("valorBruto")){
           float total =0;
           for (ConsolidadorDeVendas p : getListaMovimentacoesDiarias()) {
                total += p.getValor_venda();
            }
        
           return decFormat.format(total); 
        }  
         
            if(tipoLista.equals("2") && tipoColuna.equals("valorTroca")){
           BigDecimal total = new BigDecimal(0); 
           for (ConsolidadorDeVendas p : getListaMovimentacoesDiarias()) {
                total = total.add(p.getValorTroca());
            }
        
           return decFormat.format(total); 
        }  
         
         
        return null;
        
        
    }
    
    public int getTotalGeralQtdeLiquida() {
        int total = 0;
        
               
        
        for (ConsolidadorDeVendas p :getListaTotaisDeGrupoDeProdutos()) {
            total += p.getQtdeLiquida();
        }
        
        return total;
        
    }
    
    public int getTotalGeralQtdeVendida() {
        int total = 0;
        
        for (ConsolidadorDeVendas p :getListaTotaisDeGrupoDeProdutos()) {
            total += p.getQtde_venda();
        }
        
        return total;
        
    }
     public int getTotalGeralQtdeTroca() {
        int total = 0;
        
        for (ConsolidadorDeVendas p :getListaTotaisDeGrupoDeProdutos()) {
            total += p.getQtdeTroca();
        }
        
        return total;
        
    }
     
     public String getTotalGeralValorLiquido() {
        DecimalFormat decFormat = new java.text.DecimalFormat("#,###,##0.00");
        
        BigDecimal total = new BigDecimal(0);
        
        
        for (ConsolidadorDeVendas listaTotaisDeGrupoDeProduto : listaTotaisDeGrupoDeProdutos) {
            
            total = total.add(listaTotaisDeGrupoDeProduto.getValorLiquidoVenda());
            
        }
        
        return decFormat.format(total);  
        
    }
    
    public String getTotalGeralValorVendas() {
        
         DecimalFormat decFormat = new java.text.DecimalFormat("#,###,##0.00");
        
        float total = 0;
        
        for (ConsolidadorDeVendas p :getListaTotaisDeGrupoDeProdutos()) {
            total += p.getValor_venda();
        }
        
        return decFormat.format(total); 
        
    } 
    
    
     public String getTotalGeralValorTrocas() {
        DecimalFormat decFormat = new java.text.DecimalFormat("#,###,##0.00");
        BigDecimal total = new BigDecimal(0);
        
        for (ConsolidadorDeVendas listaTotaisDeGrupoDeProduto : listaTotaisDeGrupoDeProdutos) {
            total = total.add(listaTotaisDeGrupoDeProduto.getValorTroca());
        }
        
        return decFormat.format(total);  
        
    } 
     
    
    private String getDateToSQL() {
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	Date date = new Date();
	return dateFormat.format(date);
    }
    public boolean validaPeriodoDeVenda() throws ParseException{
        
            SimpleDateFormat ultimaDataModificacao = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
            
           // Valida Tipo de Datas de modificação.
            String dtFormattedLastModifFileMain = ultimaDataModificacao.format(consolidadorDeVendas.getDataVendaIni());
            String formattedDate = formatDate.format(consolidadorDeVendas.getDataVendaFim());
                        
            String a = dtFormattedLastModifFileMain; // Data de Modificação do arquivo principal
            String b = formattedDate; // Data de modificação do arquivo do FTP
                        
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date data1 = new Date(format.parse(a).getTime());
            Date data2 = new Date(format.parse(b).getTime());
                        
            // Comparando datas
            
            if(data1.after(data2)){
                    System.out.println("Data: " + a + " é posterior à " + b);
                    return false;
            }else if(data1.before(data2)){
                    System.out.println("Data: " + a + " é inferior à " + b);
                    return true;
            }else{
                    System.out.println("Data: " + a + " é igual à " + b);
                    return true;
            }
        
        
    }
    
     public String diaDaSemana(java.util.Date data) throws ParseException{
       Calendar cal = Calendar.getInstance();
       cal.setTime(data);
       
       int ano = cal.get(Calendar.YEAR);
       int mes = cal.get(Calendar.MONTH)+1;
       int dia = cal.get(Calendar.DAY_OF_MONTH)-2;
              
       Calendar calendario = new GregorianCalendar(ano, mes, dia);  
       int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);  
       
        return pesquisarDiaSemana(diaSemana); 
        
    }
    
    public String pesquisarDiaSemana(int _diaSemana){  
   
        String diaSemana = null;  
  
        switch (_diaSemana) {  
            case 1:{  
              diaSemana = "Dom";  
              break;  
            }  
            case 2:{  
              diaSemana = "Seg";  
              break;  
            }  
            case 3:{  
              diaSemana = "Ter";  
              break;  
            }  
            case 4:{  
              diaSemana = "Qua";  
              break;  
            }  
            case 5:{  
              diaSemana = "Qui";  
              break;  
            }  
            case 6:{  
              diaSemana = "Sex";  
              break;  
            }  
            case 7:{  
              diaSemana = "Sáb";  
              break;  
            }  
        }  
 
    return diaSemana;  
  
  } 
   
    
  public String obterMesPorExtenso(int _mes){  
   
        String mesExtenso = null;  
  
        switch (_mes) {  
            case 1:{  
              mesExtenso = "Janeiro";  
              break;  
            }  
            case 2:{  
              mesExtenso = "Fevereiro";  
              break;  
            }  
            case 3:{  
              mesExtenso = "Março";  
              break;  
            }  
            case 4:{  
              mesExtenso = "Abril";  
              break;  
            }  
            case 5:{  
              mesExtenso = "Maio";  
              break;  
            }  
            case 6:{  
              mesExtenso = "Junho";  
              break;  
            }  
            case 7:{  
              mesExtenso = "Julho";  
              break;  
            }  
            case 8:{  
              mesExtenso = "Agosto";  
              break;  
            }  
            case 9:{  
              mesExtenso = "Setembro";  
              break;  
            }  
            case 10:{  
              mesExtenso = "Outubro";  
              break;  
            }  
            case 11:{  
              mesExtenso = "Novembro";  
              break;  
            }
            case 12:{  
              mesExtenso = "Dezembro";  
              break;  
            }  
        }  
 
    return mesExtenso;  
  
  }
    
    
     //metodos para busca da grade
    public boolean filterByName(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
    
        if (filterText == null || filterText.equals("")) {
            return true;
        }

        if (value == null) {
            return false;
        }

        String myParam = value.toString().toUpperCase();
        filterText = filterText.toUpperCase();

        if (myParam.contains(filterText)) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean filterByPrice(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
    private void sendInfoMessageToUser(String message){
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }
		
    private void sendErrorMessageToUser(String message){
	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }
    
       
        
    public List<ConsolidadorDeVendas> getFilteredConsolidadorDeVendas() {
        return filteredConsolidadorDeVendas;
    }

    public void setFilteredConsolidadorDeVendas(List<ConsolidadorDeVendas> filteredConsolidadorDeVendas) {
        this.filteredConsolidadorDeVendas = filteredConsolidadorDeVendas;
    }
    
  
    
    public ConsolidadorDeVendas getConsolidadorDeVendas() {
        return consolidadorDeVendas;
    }

    public void setConsolidadorDeVendas(ConsolidadorDeVendas consolidadorDeVendas) {
        this.consolidadorDeVendas = consolidadorDeVendas;
    }

    public List<ConsolidadorDeVendas> getMyQuery() {
        return myQuery;
    }

    public void setMyQuery(List<ConsolidadorDeVendas> myQuery) {
        this.myQuery = myQuery;
    }

    public List<ConsolidadorDeVendas> getMyListFiliais() {
        return myListFiliais;
    }

    public void setMyListFiliais(List<ConsolidadorDeVendas> myListFiliais) {
        this.myListFiliais = myListFiliais;
    }

    public List<ConsolidadorDeVendas> getListaGraficoPercentualVendas() {
        return listaGraficoPercentualVendas;
    }

    public void setListaGraficoPercentualVendas(List<ConsolidadorDeVendas> listaGraficoPercentualVendas) {
        this.listaGraficoPercentualVendas = listaGraficoPercentualVendas;
    }

    public PieChartModel getGraficoPercentualDeVendas() {
        return graficoPercentualDeVendas;
    }

    public void setGraficoPercentualDeVendas(PieChartModel graficoPercentualDeVendas) {
        this.graficoPercentualDeVendas = graficoPercentualDeVendas;
    }

    public List<ConsolidadorDeVendas> getListaMovimentacoesDiarias() {
        return listaMovimentacoesDiarias;
    }

    public void setListaMovimentacoesDiarias(List<ConsolidadorDeVendas> listaMovimentacoesDiarias) {
        this.listaMovimentacoesDiarias = listaMovimentacoesDiarias;
    }

    public PieChartModel getGraficoPercentualDeLinhaProduto() {
        return graficoPercentualDeLinhaProduto;
    }

    public void setGraficoPercentualDeLinhaProduto(PieChartModel graficoPercentualDeLinhaProduto) {
        this.graficoPercentualDeLinhaProduto = graficoPercentualDeLinhaProduto;
    }

    public List<ConsolidadorDeVendas> getListaGraficoPercentualDeLinhaProduto() {
        return listaGraficoPercentualDeLinhaProduto;
    }

    public void setListaGraficoPercentualDeLinhaProduto(List<ConsolidadorDeVendas> listaGraficoPercentualDeLinhaProduto) {
        this.listaGraficoPercentualDeLinhaProduto = listaGraficoPercentualDeLinhaProduto;
    }

    public List<ConsolidadorDeVendas> getListaMovDeGrupoDeProdutos() {
        return listaMovDeGrupoDeProdutos;
    }

    public void setListaMovDeGrupoDeProdutos(List<ConsolidadorDeVendas> listaMovDeGrupoDeProdutos) {
        this.listaMovDeGrupoDeProdutos = listaMovDeGrupoDeProdutos;
    }

    public List<ConsolidadorDeVendas> getListaTotaisDeGrupoDeProdutos() {
        return listaTotaisDeGrupoDeProdutos;
    }

    public void setListaTotaisDeGrupoDeProdutos(List<ConsolidadorDeVendas> listaTotaisDeGrupoDeProdutos) {
        this.listaTotaisDeGrupoDeProdutos = listaTotaisDeGrupoDeProdutos;
    }

    public List<ConsolidadorDeVendas> getListaDeVendedores() {
        return listaDeVendedores;
    }

    public void setListaDeVendedores(List<ConsolidadorDeVendas> listaDeVendedores) {
        this.listaDeVendedores = listaDeVendedores;
    }

    public String getDataFormatadaPeriodo() {
        return dataFormatadaPeriodo;
    }

    public void setDataFormatadaPeriodo(String dataFormatadaPeriodo) {
        this.dataFormatadaPeriodo = dataFormatadaPeriodo;
    }

    public Date getDataAtualVendas() {
        return dataAtualVendas;
    }

    public void setDataAtualVendas(Date dataAtualVendas) {
        this.dataAtualVendas = dataAtualVendas;
    }

    public int getAnoAtualVendas() {
        return anoAtualVendas;
    }

    public void setAnoAtualVendas(int anoAtualVendas) {
        this.anoAtualVendas = anoAtualVendas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMesAtualVendas() {
        return mesAtualVendas;
    }

    public void setMesAtualVendas(String mesAtualVendas) {
        consolidadorDeVendas.setMesVenda(mesAtualVendas);
        this.mesAtualVendas = mesAtualVendas;
    }

    public String getFilialAtualVendas() {
        return filialAtualVendas;
    }

    public void setFilialAtualVendas(String filialAtualVendas) {
        //consolidadorDeVendas.setFilial(filialAtualVendas);
        this.filialAtualVendas = filialAtualVendas;
    }

    public String getMesAtualVendasPorExtenso() {
        return mesAtualVendasPorExtenso;
    }

    public void setMesAtualVendasPorExtenso(String mesAtualVendasPorExtenso) {
        this.mesAtualVendasPorExtenso = mesAtualVendasPorExtenso;
    }


   
    public List<ConsolidadorDeVendas> getListaDeTotaisDeVendasDiarias() {
        return listaDeTotaisDeVendasDiarias;
    }

    public void setListaDeTotaisDeVendasDiarias(List<ConsolidadorDeVendas> listaDeTotaisDeVendasDiarias) {
        this.listaDeTotaisDeVendasDiarias = listaDeTotaisDeVendasDiarias;
    }

    public int getMovDiariasCount() {
        return 1;
    }

    public void setMovDiariasCount(int movDiariasCount) {
        this.movDiariasCount = movDiariasCount;
    }

    public int getTotaisMovDiariasCount() {
        return totaisMovDiariasCount;
    }

    public void setTotaisMovDiariasCount(int totaisMovDiariasCount) {
        this.totaisMovDiariasCount = totaisMovDiariasCount;
    }

    public List<ConsolidadorDeVendas> getListaDeTotaisDeVendedoresCategorias() {
        return listaDeTotaisDeVendedoresCategorias;
    }

    public void setListaDeTotaisDeVendedoresCategorias(List<ConsolidadorDeVendas> listaDeTotaisDeVendedoresCategorias) {
        this.listaDeTotaisDeVendedoresCategorias = listaDeTotaisDeVendedoresCategorias;
    }

    public BarChartModel getGraficoGradeMedia() {
        return graficoGradeMedia;
    }

    public void setGraficoGradeMedia(BarChartModel graficoGradeMedia) {
        this.graficoGradeMedia = graficoGradeMedia;
    }

    public List<ConsolidadorDeVendas> getListaGraficoGradeMedia() {
        return listaGraficoGradeMedia;
    }

    public void setListaGraficoGradeMedia(List<ConsolidadorDeVendas> listaGraficoGradeMedia) {
        this.listaGraficoGradeMedia = listaGraficoGradeMedia;
    }

    public List<ConsolidadorDeVendas> getListaDeVendasPorCategorias() {
        return listaDeVendasPorCategorias;
    }

    public void setListaDeVendasPorCategorias(List<ConsolidadorDeVendas> listaDeVendasPorCategorias) {
        this.listaDeVendasPorCategorias = listaDeVendasPorCategorias;
    }

   
    
    
    
    
}
