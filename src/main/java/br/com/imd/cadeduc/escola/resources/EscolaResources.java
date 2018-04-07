package br.com.imd.cadeduc.escola.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.imd.cadeduc.escola.domain.Escola;
import br.com.imd.cadeduc.escola.service.EscolaService;
import br.com.imd.cadeduc.service.exception.GenericServiceException;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/escolas", produces = "application/json")
@Api(tags = "Escolas", description = "Operações pertinentes a escolas")
public class EscolaResources {

	@Autowired
	EscolaService escolaService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Escola> listar() throws GenericServiceException {
		return escolaService.listar();

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> salvar(@Valid @RequestBody Escola escola, BindingResult resultado) {
		try {
			escolaService.salvar(escola, resultado);

			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(escola.getId())
					.toUri();

			return ResponseEntity.created(uri).build();

		} catch (GenericServiceException e) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Escola> buscar(@Valid @PathVariable("id") Long id, BindingResult resultado)
			throws GenericServiceException {
		return escolaService.buscar(id, resultado);
	}
}
