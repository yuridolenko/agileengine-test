'use strict';

const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');


class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            moneyAmount: 0,
            accountId: 0,
            transactions: [],
            error: {
                code: '',
                text: ''
            }
        };
    }

    componentDidMount() {
        this.refreshTransactions();
    }

    render() {
        return (
            <div className="verticalContainer">
                <div className="container">
                    <AccountDropDownList onChange={this.onAccountChanged.bind(this)}/>
                    <input type="number" onChange={this.onMoneyChanged.bind(this)}/>
                    <MoneyButton caption="Credit" onClick={this.onMoneyButtonClick.bind(this, 'CREDIT')}/>
                    <MoneyButton caption="Dedit" onClick={this.onMoneyButtonClick.bind(this, 'DEBIT')}/>
                    <ErrorLabel error={this.state.error}/>
                </div>
                <div className="verticalContainer">
                    <button type="button" onClick={this.refreshTransactions.bind(this)}
                            className="refreshTransactionButton">Refresh
                    </button>
                    <TransactionsTable transactions={this.state.transactions}/>
                </div>
            </div>
        )
    }

    refreshTransactions() {
        let self = this;
        client({method: 'GET', path: '/api/transactions'}).done(response => {
            self.setState({
                transactions: response.entity,
                error: {
                    code: '',
                    text: ''
                }
            });
        });
    }

    onAccountChanged(e) {
        this.setState({accountId: e.target.value});
    }

    onMoneyChanged(e) {
        this.setState({moneyAmount: e.target.value});
    }

    onMoneyButtonClick(type) {
        let transaction = {accountId: this.state.accountId, type: type, amount: this.state.moneyAmount}
        client({
            method: 'PUT',
            path: '/api/transactions',
            entity: transaction,
            headers: {'Content-Type': 'application/json'}
        }).done(response => {
            this.refreshTransactions()
        }, response => {
            this.setState({
                error: {
                    code: response.status.code,
                    text: response.entity.message
                }
            });
        });
    }
}

class TransactionsTable extends React.Component {

    render() {
        var transactions = this.props.transactions.map(transaction =>
            <Transaction key={transaction.id} transaction={transaction}/>
        );
        return (
            <table className="transactionsTable">
                <tbody>
                <tr>
                    <th>Id</th>
                    <th>Account Name</th>
                    <th>Type</th>
                    <th>Amount</th>
                </tr>
                {transactions}
                </tbody>
            </table>
        )
    }
}

class Transaction extends React.Component {
    render() {
        return (
            <tr>
                <td>{this.props.transaction.id}</td>
                <td>{this.props.transaction.accountName}</td>
                <td>{this.props.transaction.type}</td>
                <td>{this.props.transaction.amount}</td>
            </tr>
        )
    }
}


class AccountDropDownList extends React.Component {
    constructor(props) {
        super(props);
        this.state = {accounts: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/accounts'}).done(response => {
            this.setState({accounts: response.entity});
        });
    }

    render() {
        var accounts = this.state.accounts.map(account =>
            <Account key={account.id} account={account}/>
        );
        return (
            <select onChange={this.props.onChange}>
                <option value="-1">Select Account...</option>
                {accounts}
            </select>
        )
    }
}

class Account extends React.Component {
    render() {
        return (
            <option value={this.props.account.id}>{this.props.account.name}</option>
        )
    }
}

class ErrorLabel extends React.Component {
    render() {
        return (
            <div className="transactionError">
                <b>{this.props.error.code}</b><span>{this.props.error.text}</span>
            </div>
        )
    }
}

class MoneyButton extends React.Component {
    render() {
        return (
            <button type="button" onClick={this.props.onClick}>{this.props.caption}</button>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('root')
)