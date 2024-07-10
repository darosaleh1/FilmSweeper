import React from 'react';
import { InventoryCount } from '../../types/InventoryTypes';

interface InventoryCountListProps {
  inventoryCounts: InventoryCount[];
}

const InventoryCountList: React.FC<InventoryCountListProps> = ({ inventoryCounts }) => {
  if (!inventoryCounts || inventoryCounts.length === 0) {
    return <div>No inventory counts available.</div>;
  }

  return (
    <ul>
      {inventoryCounts.map((inventoryCount) => (
        <li key={inventoryCount.storeId}>
          <div>Store ID: {inventoryCount.storeId}</div>
          <div>Title: {inventoryCount.title}</div>
          <div>Count: {inventoryCount.count}</div>
        </li>
      ))}
    </ul>
  );
};

export default InventoryCountList;
