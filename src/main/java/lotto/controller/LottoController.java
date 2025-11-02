package lotto.controller;

import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.service.LottoService;
import lotto.util.InputView;
import lotto.util.OutputView;

import java.util.List;

public class LottoController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final LottoService lottoService = new LottoService();

    public void run() {
        int money = inputView.inputMoney();
        List<Lotto> tickets = lottoService.buyLottos(money);
        outputView.printLottos(tickets);

        Lotto winningLotto = inputView.inputWinningNumbers();
        int bonus = inputView.inputBonusNumber(winningLotto);

        LottoResult result = lottoService.checkResult(tickets, winningLotto, bonus);
        outputView.printStatistics(result, tickets);
    }
}
