import React, { useState, useEffect } from "react";
import "./Products.css";
import { fetchProducts, addProduct } from "../services/productService";

function Products({ addToCart, isLoggedIn }) {
  const [products, setProducts] = useState([]);
  const [newProductName, setNewProductName] = useState("");
  const [newProductPrice, setNewProductPrice] = useState("");
  const [newProductImageBase64, setNewProductImageBase64] = useState("");

  // 使用 useEffect 從 REST API 獲取商品資料
  useEffect(() => {
    const loadProducts = async () => {
      try {
        const apiResponse = await fetchProducts(); // 使用查詢所有商品服務方法
        setProducts(apiResponse.data);
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };

    loadProducts();
  }, []);

  const handleAddProduct = async () => {
    // 建立新商品物件
    const newProduct = {
      name: newProductName,
      price: parseFloat(newProductPrice),
      imageBase64: newProductImageBase64,
    };

    try {
      const savedProduct = await addProduct(newProduct); // 使用新增商品服務方法
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
        console.log(reader.result);
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
        {isLoggedIn && (
          <button onClick={handleAddProduct} >新增商品</button>
        )}
        
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
                {isLoggedIn && (
                  <button onClick={() => addToCart(product)} >
                  加入購物車
                  </button>
                )}
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}

export default Products;
