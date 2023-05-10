package com.example.mosaicticket.Controller;

import com.example.mosaicticket.Model.orders.orders;
import com.example.mosaicticket.Model.tickets.Ticket;
import com.example.mosaicticket.Model.user.User;
import com.example.mosaicticket.Service.OrderService;
import com.example.mosaicticket.Service.SupplierService;
import com.example.mosaicticket.Service.TicketService;
import com.example.mosaicticket.Service.UserService;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class TicketController {

    @Autowired
    public TicketService ticketService;
    @Autowired
    private SupplierService supplierService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

//    @GetMapping("/tickets/{departure}/{arrival}")
//    public List<Ticket> findTickets(@PathVariable String departure, @PathVariable String arrival) throws Exception{
//        return ticketService.findTicket(departure, arrival);
//    }

    @GetMapping("/tickets/{departure}/{arrival}/{date}")
    public List<List<Ticket>> retrieveTickets(@PathVariable String departure, @PathVariable String arrival,
                                              @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) throws Exception{
        return supplierService.getTickets(departure, arrival, date);
    }

    @PostMapping("/orders/{username}/{orderid}/{flightid}")
    public ResponseEntity<Boolean> putOrders(@PathVariable String username, @PathVariable Long orderid, @PathVariable String flightid, @RequestBody orders inorders){
        boolean ticketbooked=supplierService.sendTicketOrder(orderid,flightid);
        User user=userService.findByUsername(username);
        System.out.println(inorders.getPassportNo()+"input orders********");

        if(ticketbooked){
            orders orderslist=new orders();
            orderslist.setFirstname(inorders.getFirstname());
            orderslist.setLastname(inorders.getLastname());
            orderslist.setEmail(inorders.getEmail());
            orderslist.setPhone(inorders.getPhone());
            orderslist.setPassportNo(inorders.getPassportNo());
            orderslist.setBirthday(inorders.getBirthday());
            orderslist.setAddress(inorders.getAddress());
            orderslist.setCity(inorders.getCity());
            orderslist.setPassportNo(inorders.getPassportNo());
            orderslist.setTicketid(inorders.getTicketid());
            orderslist.setFlightid(inorders.getFlightid());
            orderslist.setDeparture(inorders.getDeparture());
            orderslist.setArrival(inorders.getArrival());
            orderslist.setDate(inorders.getDate());
            orderslist.setOperationAirline(inorders.getOperationAirline());
            orderslist.setPrices(inorders.getPrices());
            orderslist.setUsername(username);
            orderService.saveOrder(orderslist);
        }

        return ResponseEntity.ok(ticketbooked);
    }

    @GetMapping("/orders/{username}")
    public List<orders> getOrderlist(@PathVariable String username){
        return orderService.findOrderslist(username);
    }

    @GetMapping("/orders")
    public List<orders> getOrderList(){
        return orderService.findAllOrders();
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id){
        return orderService.deleteOrderById(id);
    }


}
