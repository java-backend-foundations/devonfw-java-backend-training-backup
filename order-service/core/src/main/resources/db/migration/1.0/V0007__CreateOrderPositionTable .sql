CREATE TABLE OrderPosition (
  orderId      BIGINT NOT NULL,
  itemId       BIGINT NOT NULL,
  CONSTRAINT FK_OrderPosition_order FOREIGN KEY(orderId) REFERENCES OrderSummary(id),
  CONSTRAINT FK_OrderPosition_item FOREIGN KEY(itemId) REFERENCES Item(id)
);