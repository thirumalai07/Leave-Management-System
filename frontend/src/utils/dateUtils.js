export const dateUtils = {
  // Format date as YYYY-MM-DD
  formatDate: (date) => {
    const d = new Date(date);
    const month = String(d.getMonth() + 1).padStart(2, '0');
    const day = String(d.getDate()).padStart(2, '0');
    return `${d.getFullYear()}-${month}-${day}`;
  },

  // Format date as readable format (e.g., "Jan 15, 2024")
  formatDateReadable: (date) => {
    return new Date(date).toLocaleDateString('en-US', {
      year: 'numeric',
      month: 'short',
      day: 'numeric',
    });
  },

  // Check if date is in the past
  isPastDate: (date) => {
    return new Date(date) < new Date();
  },

  // Calculate days between two dates
  calculateDaysBetween: (startDate, endDate) => {
    const start = new Date(startDate);
    const end = new Date(endDate);
    const diffTime = Math.abs(end - start);
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    return diffDays + 1; // Inclusive of both dates
  },

  // Get current date in YYYY-MM-DD format
  getTodayDate: () => {
    return dateUtils.formatDate(new Date());
  },

  // Add days to a date
  addDaysToDate: (date, days) => {
    const d = new Date(date);
    d.setDate(d.getDate() + days);
    return dateUtils.formatDate(d);
  },
};
