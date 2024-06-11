
export function formatDateTime(date: Date) {
    if (!(date instanceof Date) || isNaN(date.getTime())) {
      return '';
    }
  
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
  
    return `${day}/${month}/${year} ${hours}:${minutes}`;
  }

  export function formatDateToRequestParam(date: Date) {
    // 2024-04-16T12:00:00

    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}T12:00:00`;
    // return date.toISOString();

  }

  export function formatDate(date: Date) {
    if (!(date instanceof Date) || isNaN(date.getTime())) {
      return '';
    }
  
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
  
    return `${day}/${month}/${year}`;
  }

  export function formatTime(date: Date) {
    if (!(date instanceof Date) || isNaN(date.getTime())) {
      return '';
    }
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
  
    return `${hours}:${minutes}`;
  }