package shou.saacas.demo.listener;

import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jp.atrealize.saacas.ssc.Packet;
import jp.atrealize.saacas.ssc.SSCReceivedStatus;
import jp.atrealize.saacas.ssc.SaacasSSCListener;

public class PassOverFragment extends Fragment {
    private static final String REQUEST_CODE_PASS_AMOUNT = "0001";

    private static final String REQUEST_CODE_QUERY = "0002";

    private static final String REQUEST_CODE_COMPLETED = "0004";

    private TextView text;

    private int amount;

    private boolean confirmed;

    public PassOverFragment() {
    }

    public void setConfirmed(boolean value) {
        confirmed = value;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pass_over, container, false);
        text = (TextView)view.findViewById(R.id.text);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        SaacasSSCListener.getInstance().listen(new SaacasSSCListener.Callback() {
            @Override
            public Packet onPacketReceived(Packet packet, SSCReceivedStatus status, int sequence) {
                Logger.info("SSC packet received (request_code='%s', data='%s', response_code='%s', status='%s', sequence='%d').", packet.getRequestCode(), packet.getData(), packet.getResponseCode(), status, sequence);

                if (REQUEST_CODE_PASS_AMOUNT.equals(packet.getRequestCode())) {
                    String[] tokens = packet.getData().split(";");
                    amount = Integer.parseInt(tokens[0]);

                    FragmentManager manager = getFragmentManager();
                    ConfirmDialogFragment dialog = ConfirmDialogFragment.newInstance(amount);
                    dialog.show(manager, "dialog");

                    return createPacket("0001");
                } else if (REQUEST_CODE_QUERY.equals(packet.getRequestCode())) {
                    if (confirmed) {
                        return createPacket("0003");
                    } else {
                        return createPacket("0002");
                    }
                } else if (REQUEST_CODE_COMPLETED.equals(packet.getRequestCode())) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            text.setText(getString(R.string.payment_completed));
                        }
                    });
                    return createPacket("0004");
                } else {
                    return createPacket("9999", "9999");
                }
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        SaacasSSCListener.getInstance().close();
    }

    private Packet createPacket(String requestCode) {
        return new Packet(requestCode, "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", "0000");
    }

    private Packet createPacket(String requestCode, String responseCode) {
        return new Packet(requestCode, "000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", responseCode);
    }
}
