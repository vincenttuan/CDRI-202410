import React, { useState, useEffect } from "react";
import "./Checkout.css";

function Checkout() {
  const [orderHistory, setOrderHistory] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchOrderHistory = async () => {
      try {
        const response = await fetch("http://localhost:8080/orders", {
          method: "GET",
          credentials: "include", // 包含 cookies 和 session
        });
  
        if (!response.ok) {
          throw new Error("無法取得結帳紀錄");
        }
  
        const apiResponse = await response.json();
        console.log("歷史訂單紀錄:", apiResponse);
        setOrderHistory(apiResponse.data);
      } catch (error) {
        console.error("Error fetching order history:", error);
      } finally {
        setLoading(false);
      }
    };
  
    fetchOrderHistory();
  }, []);

  return (
    <div className="checkout-container">
      <h1 className="checkout-title">結帳完成</h1>
      <p className="checkout-message">感謝您的購買！您的訂單已成功提交。</p>

      <h2 className="order-history-title">歷史訂單紀錄</h2>
      {loading && <p className="loading-message">載入中...</p>}
      {!loading && orderHistory.length === 0 && (
        <p className="no-orders-message">目前沒有歷史訂單。</p>
      )}
      {!loading && orderHistory.length > 0 && (
        <ul className="order-list">
          {orderHistory.map((order, index) => {
            // 計算每筆訂單的總價
            const totalAmount = order.items.reduce(
              (total, item) => total + item.product.price,
              0
            );

            return (
              <li key={index} className="order-item">
                <h3 className="order-summary">
                  訂單 #{index + 1} (訂單 ID: {order.orderUUID}, 總價: ${totalAmount})
                </h3>
                <ul className="order-items-list">
                  {order.items.map((item, itemIndex) => (
                    <li key={itemIndex} className="order-item-detail">
                      <img src={item.product.imageBase64} alt={item.product.name} width="100" valign="middle" /> {item.product.name} - ${item.product.price} - 數量: {item.quantity}
                    </li>
                  ))}
                </ul>
              </li>
            );
          })}
        </ul>
      )}
    </div>
  );
}

export default Checkout;
