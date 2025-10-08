package com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.impls;

import com.ecommerce.wlm_ecommerce_product_service.domain.exception.ProductNotFoundException;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.Order;
import com.ecommerce.wlm_ecommerce_product_service.domain.model.OrderItem;
import com.ecommerce.wlm_ecommerce_product_service.domain.repository.OrderRepository;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.adapter.mapps.OrderMapper;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.OrderItemEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.entity.ProductEntity;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataOrder;
import com.ecommerce.wlm_ecommerce_product_service.infrastructure.persistence.repository.SpringDataProduct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    public SpringDataOrder springDataOrder;
    public SpringDataProduct springDataProduct;

    public OrderRepositoryImpl(SpringDataOrder springDataOrder, SpringDataProduct springDataProduct) {
        this.springDataOrder = springDataOrder;
        this.springDataProduct = springDataProduct;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = OrderMapper.toEntity(order);

        List<OrderItemEntity> items = new ArrayList<>();
        for(OrderItem item : order.getItems()){
            ProductEntity product = springDataProduct.findById(item.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Could not find this product"));

            OrderItemEntity itemsEntity = new OrderItemEntity();
            itemsEntity.setProductEntity(product);
            itemsEntity.setQuantity(item.getQuantity());
            itemsEntity.setOrder(orderEntity); // binding
            items.add(itemsEntity);
        }
        orderEntity.setItems(items);
        return OrderMapper.toDomain(springDataOrder.save(orderEntity));
    }

    public Order findOrderById(Long orderId) {
         OrderEntity entity = springDataOrder.findById(orderId)
                .orElseThrow(() -> new NoSuchElementException("Order: ["+orderId+"] was not Found"));
         return OrderMapper.toDomain(entity);
    }

    public List<Order> findAllByUserId(Long userId) {
        List<OrderEntity> listEntity = springDataOrder.findOrdersByUserId(userId);
        List<Order> orders = new ArrayList<>();

        for(OrderEntity entity : listEntity){
            orders.add(OrderMapper.toDomain(entity));
        }

        return orders;
    }

    public void deleteOrderById(Long orderId) {
        springDataOrder.deleteById(orderId);
    }

}
