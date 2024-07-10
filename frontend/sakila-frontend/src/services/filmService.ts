import axiosInstance from '../api/axios';
import { PaginatedResponse, _Film } from '../types/FilmTypes';

export const fetchFilms = async (pageNo: number, pageSize: number = 10) => {
  const response = await axiosInstance.get<PaginatedResponse<_Film>>(`/films`, {
    params: {
      pageNo,
      pageSize,
    },
  });
  console.log('API response:', response.data);
  return response.data;
};