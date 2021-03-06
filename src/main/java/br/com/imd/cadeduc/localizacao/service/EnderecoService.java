package br.com.imd.cadeduc.localizacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import br.com.imd.cadeduc.core.dao.GenericDAO;
import br.com.imd.cadeduc.core.service.GenericService;
import br.com.imd.cadeduc.core.service.exception.GenericServiceException;
import br.com.imd.cadeduc.core.service.exception.ResourceConflictException;
import br.com.imd.cadeduc.localizacao.dao.EnderecoDAO;
import br.com.imd.cadeduc.localizacao.domain.Endereco;

@Component
public class EnderecoService extends GenericService<Endereco> {

	@Override
	public List<Endereco> listar() throws GenericServiceException {
		return super.listar();
	}

	@Override
	public void salvar(Endereco endereco, BindingResult resultado) throws GenericServiceException {
		super.salvar(endereco, resultado);
	}

	@Override
	public Optional<Endereco> buscar(Long id) throws GenericServiceException {
		return super.buscar(id);
	}

	@Autowired
	public void setDao(GenericDAO<Endereco> dao) {
		super.dao = dao;
	}

	@Override
	protected void verificaExistencia(Endereco endereco) throws GenericServiceException {
		Optional<Endereco> enderecoCadastrado = ((EnderecoDAO) dao)
				.findEnderecoByLatitudeAndLongitude(endereco.getLatitude(), endereco.getLongitude());
		
		if(endereco.getId()!=0 || enderecoCadastrado.isPresent()) {
			throw new ResourceConflictException();
		}
	}

}
