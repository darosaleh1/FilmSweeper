import axiosInstance from '../api/axios';
import { InventoryCount } from '../types/InventoryTypes';

export const fetchInventoryCount = async (storeId:number) => {
  try {
    const response = await axiosInstance.get<InventoryCount[]>(`/inventory/store/${storeId}/films`);
    console.log('API response:', response.data);
    return response.data;
  } catch (error) {
    console.error('Error fetching inventory count:', error);
    throw error;
  }
};
