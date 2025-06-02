# Task Dashboard

## Overview

A simple task management dashboard built with React, TailwindCSS, Redux, and Redux-Saga.



## Setup Instructions

### 1. Clone the repository or unzip the project

```
git clone https://github.com/pavan-gitlabh/entain-task
cd frontend-task-react
```

### 2. Install dependencies

```
npm install
```

### 3. Setup TailwindCSS

Ensure Tailwind is installed via the following command:

```
npx tailwindcss init -p
```

This will create `tailwind.config.js` and `postcss.config.js`. Use the following default config:

**tailwind.config.js**
```js
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {},
  },
  plugins: [],
}
```

**postcss.config.js**
```js
module.exports = {
  plugins: {
    tailwindcss: {},
    autoprefixer: {},
  },
}
```

### 4. Build CSS and Run

To build your Tailwind CSS and run the app in production mode:

```
npm run build
npm start
```

Or for development:

```
npm run dev
```


