package com.nttdata.reportes.repositories;

import com.nttdata.movimientos.entities.Movimiento;
import com.nttdata.reportes.dto.MovimientosDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovimientosDTORepository extends JpaRepository<Movimiento,Long> {
    @Query(value = "SELECT m.fecha as fecha, p.nombre as nombreCliente," +
            " cu.numero as numeroCuenta, cu.tipo as tipoCuenta, m.saldo_inicial as saldoInicial," +
            " cu.estado as estadoCuenta,m.valor_movimiento as montoMovimiento, cu.saldo as saldoDisponible" +
            " from cliente cl, cuenta cu, movimiento m, persona p" +
            " where cl.id=cu.cliente_id" +
            " and cu.id=m.cuenta_id" +
            " and p.id= cl.id" +
            " and m.fecha between ?1 and ?2" +
            " and cl.id=?3",nativeQuery = true)

    List<MovimientosDTO> listaMovimientosPorFechaCliente(LocalDate fechaInicio, LocalDate fechaFin, Long clienteId);
}
