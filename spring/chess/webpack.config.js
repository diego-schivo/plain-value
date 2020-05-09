const path = require('path');

module.exports = {
  mode: 'development',
  entry: './src/index.js',
  devtool: 'inline-source-map',
  output: {
    filename: 'main.js',
    path: path.resolve(__dirname, 'src/main/resources/static/dist'),
  },
  module: {
    rules: [
      {
        test: /\.m?js$/,
        exclude: /(node_modules|bower_components)/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: [
              '@babel/preset-env',
              '@babel/preset-react',
              { plugins: ['@babel/plugin-proposal-class-properties']}
            ]
          }
        }
      }
    ]
  },
  resolve: {
    alias: {
      stompjs: path.resolve(__dirname, 'node_modules/stompjs/lib/stomp.js')
    }
  }
};