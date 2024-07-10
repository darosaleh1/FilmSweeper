export type _Film = {
    id: number;
    title: string;
    description: string;
    releaseYear: string;
    language: {
      id: number;
      name: string;
    };
    originalLanguageId: number | null;
    rentalDurationInDays: number;
    rentalRate: number;
    lengthInMinutes: number;
    replacementCost: number;
    rating: string;
    specialFeatures: string[];
    lastUpdate: string;
    cast: {
      id: number;
      fullName: string;
    }[];
  };
  
  export interface PaginatedResponse<T> {
    content: T[];
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
  }
  