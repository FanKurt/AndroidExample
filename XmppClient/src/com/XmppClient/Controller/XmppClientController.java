package com.XmppClient.Controller;

import java.util.ArrayList;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.XmppClient.Adapter.ListViewAdapter;
import com.XmppClient.Dialog.SettingDialog;
import com.XmppClient.Item.MessageItem;
import com.XmppClient.Layout.XmppClientLayout;
import com.XmppClient.Module.XmppClientTag;

public class XmppClientController {
	private Context context;
	private XmppClientLayout layout;
	private XMPPConnection connection;
	private ArrayList<View> arrayList;
	public XmppClientController(Context context, XmppClientLayout layout) {
		this.context = context;
		this.layout = layout;
		init();
	}

	private void init() {
		onClickListener();
		setAdapter();
	}

	private void setAdapter() {
		arrayList = new ArrayList<View>();
		layout.getListView().setAdapter(new ListViewAdapter(arrayList));
	}

	private void onClickListener() {
		layout.getButtonSend().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				String to = XmppClientTag.TO;
				String text = layout.getEdit().getText().toString();

				Log.e("XMPPClient", "Sending text [" + text + "] to [" + to
						+ "]");
				Message msg = new Message(to, Message.Type.chat);
				msg.setSubject("chat");
				msg.setBody("c" + text);
				connection.sendPacket(msg);
//				addItemView(text);
			}

			private void addItemView(String text) {
				MessageItem massage = new MessageItem(context);
				massage.getTextView().setText("text1 : " + text);
				arrayList.add(massage);
				((ListViewAdapter) layout.getListView().getAdapter())
						.notifyDataSetChanged();
			}
		});

		layout.getButtonSetting().setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				final SettingDialog dialog = new SettingDialog(context);
				dialog.Show();
				dialog.getButton().setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						String host = dialog.getHost().getText().toString();
						String port = dialog.getPort().getText().toString();
						String service = dialog.getService().getText()
								.toString();
						String username = dialog.getUser().getText().toString();
						String password = dialog.getPass().getText().toString();

						// Create a connection
						ConnectionConfiguration connConfig = new ConnectionConfiguration(
								host, Integer.parseInt(port), service);
						connection = new XMPPConnection(connConfig);

						try {
							connection.connect();
							connection.login(username, password);

							// Set the status to available
							Presence presence = new Presence(Presence.Type.available);
							connection.sendPacket(presence);
							setConnection(connection);

						} catch (XMPPException ex) {
							Log.e("XMPPClient", ex.toString());
							setConnection(null);
						}
						dialog.Dismiss();
					}
				});
			}
		});
	}

	private void setConnection(XMPPConnection connection) {
		if (connection != null) {
			PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
			connection.addPacketListener(new PacketListener() {
				public void processPacket(Packet packet) {
					Message message = (Message) packet;
					if (message.getBody() != null) {
						String fromName = StringUtils.parseBareAddress(message.getFrom());
						Log.e("XMPPClient", "Got text [" + message.getBody()+ "] from [" + fromName + "]");
					}
				}
			}, filter);
		}
	}

}
