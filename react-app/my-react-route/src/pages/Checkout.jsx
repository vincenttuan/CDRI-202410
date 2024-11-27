import React, { useState, useEffect } from "react";
import "./Checkout.css";

function Checkout() {
  
  return (
    <div className="checkout-container">
      <h1 className="checkout-title">結帳完成</h1>
      <p className="checkout-message">感謝您的購買！您的訂單已成功提交。</p>
      <h2 className="order-history-title">歷史訂單紀錄</h2>
     
    </div>
  );
}

export default Checkout;
