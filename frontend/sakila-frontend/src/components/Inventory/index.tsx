import React, { useState } from 'react';
import InventoryButton from './InventoryButton';
import { fetchInventoryCount } from '../../services/inventoryService';
import InventoryCountList from './InventoryCountList';
import { InventoryCount } from '../../types/InventoryTypes';
import './Inventory.css';

const Inventory: React.FC = () => {
  const [showInput, setShowInput] = useState(false);
  const [storeId, setStoreId] = useState<number | null>(null);
  const [inventoryCounts, setInventoryCounts] = useState<InventoryCount[] | null>(null);
  const [error, setError] = useState<string | null>(null);

  const handleButtonClick = () => {
    setShowInput(true);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setStoreId(parseInt(e.target.value));
  };

  const handleFetchInventory = async () => {
    if (storeId !== null) {
      try {
        const data = await fetchInventoryCount(storeId);
        setInventoryCounts(data);
        setError(null);
      } catch (err) {
        setError('Error fetching inventory count');
        setInventoryCounts(null);
      }
    }
  };

  return (
    <div className="inventory-container">
      <h1>Welcome to the inventory page</h1>
      <InventoryButton text="Search by store?" onClick={handleButtonClick} />
      {showInput && (
        <div className="input-group">
          <label htmlFor="storeId">Enter Store ID:</label>
          <input className="store-id-input" type="number" id="storeId" name="storeId" onChange={handleInputChange} />
          <button className="fetch-button" onClick={handleFetchInventory}>Fetch Inventory</button>
        </div>
      )}
      {error && <div className="error-message">{error}</div>}
      {inventoryCounts && <InventoryCountList inventoryCounts={inventoryCounts} />}
    </div>
  );
};

export { Inventory };
