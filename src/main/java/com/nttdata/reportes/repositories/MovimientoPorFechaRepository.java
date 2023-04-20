package com.nttdata.reportes.repositories;

import com.nttdata.reportes.dto.MovimientosPorFecha;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface MovimientoPorFechaRepository extends CrudRepository<MovimientoPorFechaRepository,Long> {

    @Query("SELECT new com.nttdata.reportes.dto.MovimientosPorFecha " +
            "(m.fecha, cl.nombre, cu.numero,cu.tipo,m.saldoInicial, cu.estado, " +
            "m.valorMovimiento, cu.saldo) " +
            "from Cliente cl, Cuenta cu, Movimientos m " +
            "where cl.id=cu.cliente_id and cu.id=m.cliente_id "  +
            "and m.fecha between ?1 and ?2  and cl.id=?3")
    List<MovimientosPorFecha> listaMovimientosPorFechaCliente(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId);
}
