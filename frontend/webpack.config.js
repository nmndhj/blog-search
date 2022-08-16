const path = require('path');
const dotenv = require('dotenv');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const ForkTsCheckerWebpackPlugin = require('fork-ts-checker-webpack-plugin');

module.exports = (env) => {
  dotenv.config({
    path: `./env/${env.properties}.env`,
  });

  return {
    entry: path.resolve(__dirname, './src/index.tsx'),
    output: {
      path: path.resolve(__dirname, 'dist/'),
      publicPath: '/',
    },
    module: {
      rules: [
        {
          test: /\.(ts|tsx)$/,
          use: [
            'babel-loader',
            {
              loader: 'ts-loader',
              options: {
                transpileOnly: true,
              },
            },
          ],
          exclude: /node_modules/,
        },
      ],
    },
    resolve: {
      extensions: ['.tsx', '.ts', '.js'],
    },
    plugins: [
      new HtmlWebpackPlugin({
        template: './src/index.html',
        filename: 'index.html',
      }),
      new ForkTsCheckerWebpackPlugin(),
      new webpack.DefinePlugin({
        'process.env.API_URL': JSON.stringify(process.env.API_URL),
      }),
    ],
    devServer: {
      historyApiFallback: true,
      allowedHosts: 'all',
      proxy: {
        '/': {
          target: 'http://127.0.0.1:8081',
        },
        '/auth': {
          target: 'http://127.0.0.1:8081',
        },
      },
    },
  };
};
