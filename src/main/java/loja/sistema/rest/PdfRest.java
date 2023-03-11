package loja.sistema.rest;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bouncycastle.pqc.crypto.qtesla.QTeslaKeyEncodingTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.io.Files;

import loja.sistema.model.Pedido;
import loja.sistema.model.Product;
import loja.sistema.repository.PedidoRepository;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@RestController
@RequestMapping("api/pdf")
@CrossOrigin
public class PdfRest {
	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping(value = "/pedido/{id}/Pedido.pdf")
	public String pdfPedidoById(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
		Pedido pedido = new Pedido();
		System.out.println(id);
		pedido = pedidoRepository.findById(id).get();
		System.out.println(pedido);
		HashMap<Object, Object> teste = new HashMap<>();
		teste.put(pedido.getProduct(), teste);
		System.out.println(teste);
		
		try {
			JasperReport report = JasperCompileManager
					.compileReport(getClass().getResourceAsStream("/loja/sistema/relatorio/PdfPedido.jrxml"));
			Map<String, Object> map = new HashMap<>();
			map.put("nomeCompleto", pedido.getNomeCompleto());
			map.put("quantidadeDeProduct", pedido.getQuantidadeDeProduct());
			map.put("descricaoDoProduct", pedido.getProduct());
			map.put("precoDoProduct",  pedido.getProduct());
			map.put("precoTotal", pedido.getPrecoTotal());

			String name = "PEDIDO.pdf";

			JasperPrint print = JasperFillManager.fillReport(report, map, new JREmptyDataSource());

			JasperExportManager.exportReportToPdfFile(print, name);

			File arquivo = new File(name);

			OutputStream output = response.getOutputStream();

			Files.copy(arquivo, output);

			output.close();

		} catch (JRException e) {
			
			e.printStackTrace();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		return "ok";

	}

}
