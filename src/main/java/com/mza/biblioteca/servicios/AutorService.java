/*
 * Sin licencia.
 * Uso para capacitación
 * 2021 Año de la Prevención y Lucha contra el COVID-19.
 */
package com.mza.biblioteca.servicios;

import com.mza.biblioteca.entidades.Autor;
import com.mza.biblioteca.excepciones.MiExcepcion;
import com.mza.biblioteca.repositorios.RepoAutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Adrian E. Camus
 */
@Service
public class AutorService {

    @Autowired
    private RepoAutor aRepo;

    @Transactional
    public void creaAutor(String nombre) throws MiExcepcion {

        validar(nombre);

        Autor autor = new Autor();
        //List<Libro> listaLibros = new ArrayList<>();
        //autor.setLibros(listaLibros);
        autor.setAlta(Boolean.TRUE);
        autor.setNombre(nombre);

        aRepo.save(autor);
    }


    @Transactional(readOnly = true)
    public List<Autor> buscaAutores() {
        return aRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Autor buscaPorNombre(String nombre) {
        return aRepo.buscaPorNombre(nombre);
    }

    @Transactional(readOnly = true)
    public Autor buscaPorId(String id) throws MiExcepcion {
        Optional<Autor> optional = aRepo.findById(id);
        
        if (optional.isPresent())
        {
            return optional.get();
        }else{
            throw new MiExcepcion("No hay autor con ese id");
        }            
        
    }

    private void validar(String nombre) throws MiExcepcion {

        if (nombre.trim().isEmpty() || nombre == null)
        {
            throw new MiExcepcion("Nombre no valido");
        }

    }
}
