import React, { useState, useEffect } from "react";
import "./Products.css";

function Products({ addToCart, isLoggedIn }) {
  const [products, setProducts] = useState([]);
  const [newProductName, setNewProductName] = useState("");
  const [newProductPrice, setNewProductPrice] = useState("");
  const [newProductImageBase64, setNewProductImageBase64] = useState("");

  // 使用 useEffect 從 REST API 獲取商品資料
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch("http://localhost:8080/products", {
          method: "GET",
          credentials: "include", // 包含 cookies 和 session
        });

        if (!response.ok) {
          throw new Error("無法取得產品資料");
        }

        const apiResponse = await response.json();
        setProducts(apiResponse.data);
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };

    fetchProducts();
  }, []);

  const handleAddProduct = async () => {
    // 建立新商品物件
    const newProduct = {
      name: newProductName,
      price: parseFloat(newProductPrice),
      imageBase64: newProductImageBase64,
    };

    try {
      const response = await fetch("http://localhost:8080/products", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(newProduct),
      });

      if (!response.ok) {
        throw new Error("新增商品失敗");
      }

      const savedProduct = await response.json();
      // 將新商品加入到商品列表
      setProducts([...products, savedProduct.data]);

      // 清空表單輸入框
      setNewProductName("");
      setNewProductPrice("");
      setNewProductImageBase64("");
    } catch (error) {
      console.error("Error adding product:", error);
    }
  };

  const handleImageUpload = (e) => {
    const file = e.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setNewProductImageBase64(reader.result);
      };
      reader.readAsDataURL(file);
    }
  };

  return (
    <div className="products-container">
      <div className="add-product-form">
        <h2>新增商品</h2>
        <input
          type="text"
          placeholder="商品名稱"
          value={newProductName}
          onChange={(e) => setNewProductName(e.target.value)}
        />
        <input
          type="number"
          placeholder="價格"
          value={newProductPrice}
          onChange={(e) => setNewProductPrice(e.target.value)}
        />
        <input
          type="file"
          accept="image/*"
          onChange={handleImageUpload}
        />
        <button onClick={handleAddProduct} disabled={!isLoggedIn}>新增商品</button>
      </div>
      <div className="product-list">
        <h1>商品列表</h1>
        {products.length === 0 ? (
          <p>無商品資料...</p>
        ) : (
          <ul>
            {products.map((product) => (
              <li key={product.id}>
                <span>
                <img src={product.imageBase64} alt={product.name} valign="middle"/> {product.name} - ${product.price}
                </span>
                <button onClick={() => addToCart(product)} disabled={!isLoggedIn}>
                  加入購物車
                </button>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}

export default Products;
