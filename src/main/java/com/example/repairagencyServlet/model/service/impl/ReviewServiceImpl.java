package com.example.repairagencyServlet.model.service.impl;


/*
@Service
public class ReviewServiceImpl implements ReviewService{
    private DaoFactory daoFactory = DaoFactory.getInstance();
    OrderService orderService = new OrderServiceImpl();



    @Override
    public List<Review> findAllReviewsByMasterId(Long id) {
        return daoFactory.createReviewDao().findAllByMasterId();
    }

    @Override
    public Review leaveFeedback(String feedback, Long orderId) {
        Order order = orderService.findOrderById(orderId);
        Long masterId = order.getMaster().getId();
        AppUser updatedMaster = appUserService.findById(masterId);
        Review review = daoFactory.createReviewDao().create().save(Review.builder()
                .master(updatedMaster)
                .reviewDescription(feedback)
                .build());
        order.setOrderStatus(OrderStatus.REVIEWED);
        orderService.save(order);

        return review;
    }
}
*/
