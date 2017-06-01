'use strict';

const React = require('react');
const ReactDOM = require('react-dom')
const client = require('./client');


class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {moneyAmount: 0, accountId: 0};
    }

    render() {
        return (
            <div className="verticalContainer">
                <div className="container">
                    <AccountDropDownList onChange={this.onAccountChanged}/>
                    <input type="number" onChange={this.onMoneyChanged.bind(this)}/>
                    <MoneyButton caption="Credit" onClick={this.onMoneyButtonClick.bind(this, 'CREDIT')}/>
                    <MoneyButton caption="Dedit" onClick={this.onMoneyButtonClick.bind(this, 'DEBIT')}/>
                </div>
                <TransactionsTable />
            </div>
        )
    }

    onAccountChanged(e) {
        this.setState({accountId: e.target.value});
    }

    onMoneyChanged(e) {
        this.setState({moneyAmount: e.target.value});
    }

    onMoneyButtonClick(type) {
        let transaction = {accountId: this.state.accountId, type: type, amount: this.state.moneyAmount}
        client({method: 'PUT', path: '/api/transactions', entity: transaction}).done(response => {
            console.log(response)
        });
    }
}

class TransactionsTable extends React.Component {

    constructor(props) {
        super(props);
        this.state = {transactions: []};
    }

    refresh() {
        let self = this;
        client({method: 'GET', path: '/api/transactions'}).done(response => {
            self.setState({transactions: response.entity});
        });
    }

    componentDidMount() {
        this.refresh();
    }

    render() {
        var transactions = this.state.transactions.map(transaction =>
            <Transaction key={transaction.id} transaction={transaction}/>
        );
        return (
            <div className="verticalContainer">
                <button type="button" onClick={this.refresh.bind(this)} className="refreshTransactionButton">Refresh</button>
                <table>
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
            </div>
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