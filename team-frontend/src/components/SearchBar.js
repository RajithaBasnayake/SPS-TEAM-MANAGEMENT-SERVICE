import React, { useState } from 'react';

function SearchBar({ onSearch }) {
  const [searchTerm, setSearchTerm] = useState('');

  const handleSearch = (e) => {
    e.preventDefault();
    onSearch(searchTerm);
  };

  const handleClear = () => {
    setSearchTerm('');
    onSearch('');
  };

  return (
      <div className="search-bar">
        <form onSubmit={handleSearch} className="search-form">
          <input
              type="text"
              placeholder="🔍 Search teams by name..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              className="search-input"
          />
          <button type="submit" className="btn btn-search">
            Search
          </button>
          {searchTerm && (
              <button type="button" className="btn btn-clear" onClick={handleClear}>
                Clear
              </button>
          )}
        </form>
      </div>
  );
}

export default SearchBar;
