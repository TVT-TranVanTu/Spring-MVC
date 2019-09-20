package quanlythongtin.service;

import java.util.List;
import quanlythongtin.model.Customer;

public interface CustomerService {

    List<Customer>findAll();
    void save(Customer customer);
    Customer findByid(int id);
    void update(int id,Customer customer);
    void remove(int id);
}
